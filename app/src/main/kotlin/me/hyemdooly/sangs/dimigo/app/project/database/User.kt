package me.hyemdooly.sangs.dimigo.app.project.database

import io.realm.RealmObject

/**
 * Created by songhyemin on 2017. 8. 24..
 */
public open class User : RealmObject() {
    public open var level : Int? = null
    public open var totalTime: Int? = null // 누적
    public open var curTime: Int? = null // 레벨업한후 쌓인 시간, 레벨업하면 비움
}