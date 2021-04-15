package com.laboratory.anyrandom.util

import com.laboratory.anyrandom.bean.VerseBean

object FoyouOthers {
    fun getFoyouOthers(): VerseBean {
        val arrayStr: Array<VerseBean> = arrayOf(
            // 现代诗词
            VerseBean("马蹄留下踏残的落花，在南国小小的山径。歌人留下了破碎的琴韵，在北方幽幽的寺院。", "痖弦", "秋歌"),
            VerseBean("我们不过是穷乏的小孩子。偶然想假装富有，脸便先红了。", "郑振铎", "赤子之心·赠圣陶"),
            // 名言
            VerseBean("世人如蝼蚁，笼中把玩物。", "", ""),
            VerseBean("教育植根于爱。", "鲁迅", ""),
            VerseBean("如果你没被你发布的第一版产品尴尬到，你发布地已经太晚了。", "Reid Hoffman", ""),
            VerseBean("地上的人多心不平，天上的星多月不亮。", "", "江阴民谚"),
            VerseBean("贫穷让我们无法入睡，醒来这个世界会好吗？", "阿霜", ""),
            VerseBean("寂静的墙和寂静的我之间，野花膨胀着花蕾，不尽的路途在不尽的墙间延展，有很多事要慢慢对它谈，随手记下谓之写作。", "史铁生", ""),
            VerseBean("指南针如何导航，你是我唯一方向。", "蒲熠星、郭文韬", ""),
            VerseBean("怎么，暴动吗？不，陛下，是革命。", "", "得知巴士底狱被攻占后，法国国王路易十六与利昂古尔公爵的一段对话"),
            VerseBean("Hello, world!", "Brian Kernighan", ""),
            VerseBean("再好的设计也无法拯救低质量的内容。", "Edward R. Tufte", ""),

            // foyou
            VerseBean("加油！", "", "Foyou"),
            VerseBean("记得今天星期几吗？", "", "Foyou"),
            VerseBean("！", "", "Foyou"),
            VerseBean("笑一笑。", "", "Foyou"),
            VerseBean("夏日当空，心如深渊。", "", "Foyou"),
            VerseBean("白白胖胖，充满希望。", "", "Foyou"),
            VerseBean("无聊吗？不如给我个好评。", "", "Foyou"),
            VerseBean("求五星好评(❁´◡`❁)", "", "Foyou"),
            VerseBean("那天我走出教室，对身边擦肩而过的他轻声说了一句：我喜欢你。", "", ""),

            // 游戏
            VerseBean("人生百年，吾道不孤。总有人和我们一起的。", "岑缨", "古剑奇谭三：梦付千秋星垂野"),
            VerseBean("不应该放弃，直到失去的那一刻。", "上海软星", "仙剑奇侠传四"),
            VerseBean("涛山阻绝秦帝船，汉宫彻夜捧金盘。玉肌枉然生白骨，不如剑啸易水寒。", "", "世上岂有神仙哉"),
            VerseBean("那些美好的回忆便寂静地躺在那里，被岁月覆盖。飘落后才发现这一地的幸福碎片，要我怎么拣。", "上海软星", ""),
            VerseBean("我们为赎罪而活，但是否又在不断犯下新的罪？", "重光", "仙剑奇侠传四"),

            VerseBean("仰人鼻息，朝夕可亡。", "", "古剑奇谭三：梦付千秋星垂野"),
            VerseBean("黑火、妖魔，或是轩辕剑。你想先知道哪件事？", "水镜", "轩辕剑柒"),
            VerseBean("犹豫，就会败北。", "苇名一心", "只狼：影逝二度"),
            VerseBean("嘎！", "大鹅", "无题大鹅模拟"),
            VerseBean("这个东西要是能量产一定大卖！", "哎哟喂博士", "路易吉洋楼3"),

            VerseBean("我再强调一次！宝可梦是我们重要的伙伴！", "洛兹", "宝可梦：剑盾"),
            VerseBean("白骨之后，重走西游。", "", "黑神话：悟空"),
            VerseBean("苍天弃吾，吾宁成魔！", "玄霄", "仙剑奇侠传四"),
            VerseBean("天道作何，吞恨者多。千秋竟岁，伏苦飞逐。昆仑有玉，以为兵戈。山遥海阔，万世奋飞。", "", "古剑奇谭三：梦付千秋星垂野"),

            // 影视
            VerseBean("如果再也不能见到你，祝你早安，午安，晚安。", "", "楚门的世界"),
            VerseBean("陆涛问：如果我一辈子穷困，你还会爱我吗？夏琳回答：如果你一辈子努力，即使穷困我也还爱你。", "", "奋斗"),
            VerseBean("不要忘记你的名字才能找到回家的路。", "宫崎骏", "千与千寻"),
            VerseBean("After all this times?Always.", "", "哈利波特"),
            VerseBean("只要我们住在对方心里，死亡就不是分离。", "", "北京遇上西雅图"),
            VerseBean("你要活，我陪你；你想死，也陪你。", "", "毒战"),
            VerseBean("你若遇上麻烦，不要逞强，你就跑，远远跑开。", "", "阿甘正传"),
            VerseBean("做羹要讲究火候，火候不到，众口难调，火候过了，事情就焦，做人也是一样。", "", "一代宗师"),
            VerseBean("人人心中都有个龙猫，童年就永远不会消失。", "宫崎骏", "龙猫"),
            VerseBean("拯救一人，如同拯救整个世界。", "", "辛德勒名单"),
            VerseBean("不要忘记你的名字才能找到回家的路。", "", "千与千寻"),
            VerseBean("所以爱会消失对不对？", "", "我们与恶的距离"),

            // 歌词
            VerseBean("小时候处处都充满惊喜，现在都去了哪里？", "", "无论你多怪异我还是会喜欢你"),
            VerseBean("担当生前事，何计身后评。", "王健", "毛阿敏 - 历史的天空"),
        )
        val index = (0..arrayStr.lastIndex).random()
        return VerseBean(arrayStr[index].text, arrayStr[index].author, arrayStr[index].source)
    }
}