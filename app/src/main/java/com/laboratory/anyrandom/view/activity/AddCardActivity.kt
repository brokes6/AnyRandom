package com.laboratory.anyrandom.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gyf.immersionbar.ImmersionBar
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.databinding.ActivityAddCradBinding
import com.laboratory.anyrandom.eventbus.MessageWrap
import com.laboratory.anyrandom.eventbus.REFRESH
import com.laboratory.anyrandom.override.BlurTransformation
import com.laboratory.anyrandom.viewmolder.HomeViewModel
import com.yalantis.ucrop.UCrop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class AddCardActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddCradBinding
    private lateinit var viewModel: HomeViewModel
    private var bitmap: Bitmap? = null

    companion object {
        private const val REQUEST_CODE_ALBUM = 1001 //相册
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = ActivityAddCradBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ImmersionBar.with(this)
            .statusBarColor(R.color.White)
            .fitsSystemWindows(true)
            .statusBarDarkFont(true)
            .init()
    }

    override fun initView() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.choiceImage.setOnClickListener(this)
        binding.push.setOnClickListener(this)
        binding.cardBack.setOnClickListener(this)
    }

    override fun initData() {
        viewModel.detailData.observe(this, {
            binding.push.revertAnimation()
            EventBus.getDefault().post(MessageWrap(REFRESH))
            Toast.makeText(this@AddCardActivity, "提交成功！", Toast.LENGTH_SHORT).show()
            finish()
        })
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.choiceImage -> {
                choiceImage()
            }
            R.id.push -> {
                if (binding.inputRandomName.text.toString() == "") {
                    Toast.makeText(this, "请将参数补充完整！", Toast.LENGTH_SHORT).show()
                    return
                }
                binding.push.startAnimation()
                GlobalScope.launch {
                    App.getDatabase(this@AddCardActivity)?.homeDao?.getHomeData().also {
                        if (it == null) {
                            saveImage(saveBitmap(bitmap!!, "image"))
                        } else {
                            saveImage(saveBitmap(bitmap!!, "image${it.size + 1}"))
                        }
                    }
                }
            }
            R.id.cardBack -> {
                finish()
            }
        }
    }

    private fun choiceImage() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //这里是SDK小于29，且没有获取到权限
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                1
            )
        } else {
            openAlbum()
        }
    }

    private fun openAlbum() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = "android.intent.action.GET_CONTENT"
        intent.addCategory("android.intent.category.OPENABLE")
        startActivityForResult(intent, REQUEST_CODE_ALBUM)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openAlbum()
        } else {
            Toast.makeText(this, "您拒绝了打开相册", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ALBUM -> {
                    doCrop(data!!.data!!)
                }
                UCrop.REQUEST_CROP -> {
                    val resultUri: Uri = UCrop.getOutput(data!!)!!
                    Glide.with(this)
                        .load(BitmapFactory.decodeStream(contentResolver.openInputStream(resultUri)))
                        .apply(
                            RequestOptions.bitmapTransform(BlurTransformation(25, 20))
                        ).into(binding.image)
                    bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(resultUri))
                }
                UCrop.RESULT_ERROR -> {
                    val error: Throwable = UCrop.getError(data!!)!!
                    Toast.makeText(this, "图片剪裁失败 + $error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun doCrop(sourceUri: Uri) {
        UCrop.of(sourceUri, getDestinationUri())//当前资源，保存目标位置
            .withAspectRatio(1f, 1.2f)//宽高比
//            .withMaxResultSize(500, 600)//宽高
            .start(this)
    }

    private fun getDestinationUri(): Uri {
        val fileName = String.format("fr_crop_%s.jpg", System.currentTimeMillis())
        val cropFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        return Uri.fromFile(cropFile)
    }

    private fun saveImage(imagePaht: String) {
        GlobalScope.launch(Dispatchers.IO) {
            App.getDatabase(this@AddCardActivity)?.homeDao?.insertAll(
                HomeDetailBean(
                    binding.inputRandomName.text.toString(),
                    binding.inputRandomCount.text.toString().toInt(),
                    binding.inputRandomCategory.text.toString(),
                    binding.inputTitleIntroduce.text.toString(),
                    imagePaht,
                    2
                )
            )
            viewModel.getData(this@AddCardActivity)
        }
    }

    private fun saveBitmap(bitmap: Bitmap, value: String): String {
        val bitMapFile = File(this.filesDir.path, value)
        if (!bitMapFile.exists()) {
            try {
                bitMapFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            val out = FileOutputStream(bitMapFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitMapFile.path
    }

}