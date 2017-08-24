package me.hyemdooly.sangs.dimigo.app.project.database

import io.realm.RealmObject

/**
 * Created by songhyemin on 2017. 8. 24..
 */
public open class Achieve : RealmObject() {
    public open var title : String? = null // 업적 이름
    public open var time: Int? = null // 목표시간
    public open var state: Boolean? = null // 상태, 했는지 안했는지
}