package com.laboratory.anyrandom.util

import com.laboratory.anyrandom.bean.VerseBean

object FoyouPoetry {

    fun getFoyouPoetry(): VerseBean {
        val arrayStr: Array<VerseBean> = arrayOf(
            VerseBean("#汉之广矣，不可泳思。江之永矣，不可方思。", "", "诗经·国风·周南·汉广"),
            VerseBean("山际见来烟，竹中窥落日。鸟向檐上飞，云从窗里出。", "吴均", "山中杂诗"),
            VerseBean("春江秋月冬冰雪，不听陈言只听天。", "杨万里", "读张文潜诗"),
            VerseBean("寄意寒星荃不察，我以我血荐轩辕。", "鲁迅", "自题小像"),
            VerseBean("纵使晴明无雨色，入云深处亦沾衣。", "张旭", "山中留客"),
            VerseBean("为报先生归也，杏花春雨江南。", "虞集", "风入松"),
            VerseBean("枕上诗书闲处好，门前风景雨来佳。", "李清照", "摊破浣溪沙"),
            VerseBean("人生天地间，忽如远行客。", "", "古诗十九首"),
            VerseBean("慈恩塔下题名处，十七人中最少年。", "白居易", ""),
            VerseBean("一夜梦游千里月，五更霜落万家钟。", "濮淙", "闻梁蘧玉己寓京口"),
            VerseBean("正是江南好风景，落花时节又逢君。", "杜甫", "江南逢李龟年"),
            VerseBean("有约不来过夜半，闲敲棋子落灯花。", "赵师秀", "约客"),
            VerseBean("自古美人如名将，不许人间见白头。", "艳雪", ""),
            VerseBean("一片寒微骨，翻成面面心。自从遭点污，抛掷到如今。", "佚名", ""),
            VerseBean("迢迢牵牛星，皎皎河汉女。", "", "古诗十九首·迢迢牵牛星"),
            VerseBean("思君令人老，岁月忽已晚。", "", "古诗十九首·行行重行行"),
            VerseBean("哀哀父母，生我劬劳。", "", "诗经·小雅·谷风之什·蓼莪"),
            VerseBean("诗豪与风雪争先，风雪与诗鏖战，诗和雪缴缠，一笑琅然。", "孙周卿", "水仙子·舟中"),
            VerseBean("老骥伏枥，志在千里；烈士暮年，壮心不已。", "曹操", "步出夏门行·龟虽寿"),
            VerseBean("盲人骑瞎马，夜半临深池。", "刘义庆", "世说新语"),
            VerseBean("知我者，谓我心忧；不知我者，谓我何求。悠悠苍天，此何人哉？", "", "诗经·黍离"),
            VerseBean("长安故人问我，道愁肠泥酒只依然。", "辛弃疾", "木兰花慢"),
            VerseBean("翩若惊鸿，婉若游龙。荣曜秋菊，华茂春松。", "曹植", "洛神赋"),
            VerseBean("两脚踢翻尘世路，一肩担尽古今愁。", "传为袁枚所作", ""),
            VerseBean("兴，百姓苦；亡，百姓苦。", "张养浩", "山坡羊·潼关怀古"),
            VerseBean("家国兴亡自有时，吴人何苦怨西施。", "罗隐", "西施"),
            VerseBean("万一禅关砉然破，美人如玉剑如虹。", "龚自珍", "夜坐二首"),
            VerseBean("今人嗤点流传赋，不觉前贤畏后生。", "杜甫", "戏为六绝句"),
            VerseBean("洛阳城里春光好，洛阳才子他乡老。", "韦庄", "菩萨蛮"),
            VerseBean("南朝四百八十寺，多少楼台烟雨中。", "杜牧", "江南春"),
            VerseBean("江东子弟多才俊，卷土重来未可知。", "杜牧", "题乌江亭"),
            VerseBean("十年一觉扬州梦，赢得青楼薄幸名。", "杜牧", "遣怀"),
            VerseBean("一壶酒，一竿纶，世上如侬有几人？", "李煜", "渔父"),
            VerseBean("世人作梅词，下笔便俗。予试作一篇，乃知前言不妄耳。", "李清照", "孤雁儿·并序"),
            VerseBean("此夜相思君负我，他日相忘我负君。流年各自珍。", "杜随", "破阵子"),
            VerseBean("败叶敲窗，西风满院，睡不成还起。", "柳永", "十二时（秋夜）"),
            VerseBean("人到愁来无会处，不关情处也伤心！", "王恽", "双鸳鸯·乐府合欢曲"),
            VerseBean("落日晴江里，荆歌艳楚腰。采莲从小惯，十五即乘潮。", "刘方平", "采莲曲"),
            VerseBean("新作蛾眉样，谁将月里同。有来凡几日，相效满城中。", "刘方平", "京兆眉"),
            VerseBean("谁谓高人无继没，至今名画落瑶天。", "Foyou", "梅"),
            VerseBean("结发为夫妻，恩爱两不疑。", "苏武", ""),
            VerseBean("肃肃秋风起，悠悠行万里。万里何所行，横漠筑长城。", "杨广", "饮马长城窟行"),
            VerseBean("醉卧沙场君莫笑，古来征战几人回？", "王翰", "凉州词"),
            VerseBean("今日欢呼孙大圣，只缘妖雾又重来。", "毛泽东", "七律·和郭沫若同志"),
            VerseBean("入郭登桥出郭船，红楼日日柳年年。君王忍把平陈业，只博雷塘数亩田。", "罗隐", "炀帝陵"),
            VerseBean("采得百花成蜜后，为谁辛苦为谁甜。", "罗隐", "蜂"),
            VerseBean("欲买桂花同载酒，终不似，少年游。", "刘过", "唐多令"),
            VerseBean("一片芳心千万绪，人间没个安排处。", "李煜", "蝶恋花"),
            VerseBean("还似旧时游上苑，车如流水马如龙。花月正春风。", "李煜", "望江南·多少恨"),
            VerseBean("高处不胜寒。起舞弄清影，何似在人间。", "苏轼", "水调歌头"),
            VerseBean("未到江南先一笑，岳阳楼上看君山。", "王庭坚", "雨中登岳阳楼望君山"),
            VerseBean("总角问道，白首无成。", "陶渊明", "荣木"),
            VerseBean("西风残照，汉家陵阙。", "李白", "忆秦娥"),
            VerseBean("茕茕白兔，东走西顾。衣不如新，人不如故。", "", "古艳歌"),
            VerseBean("了却君王天下事，赢得生前身后名。可怜白发生。", "辛弃疾", "破阵子·为陈同甫赋壮词以寄之"),
            VerseBean("鹏北海，凤朝阳。又携书剑路茫茫。", "辛弃疾", "鹧鸪天"),
            VerseBean("对酒不觉眠，落花盈我衣。醉起步溪月，鸟还人亦稀。", "李白", "自遣"),
            VerseBean("竹斋眠听雨，梦里长青苔。", "方岳", "听雨"),
            VerseBean("杯盘罢，争些醉煞，和月宿芦花。", "赵显宏", "中吕·满庭芳"),
            VerseBean("若无闲事挂心头，便是人间好时节。", "释梵思", "颂古九首"),
        )
        val index = (0..arrayStr.lastIndex).random()
        return VerseBean(arrayStr[index].text, arrayStr[index].author, arrayStr[index].source)
    }

}