package me.hyemdooly.sangs.dimigo.app.project.database

import io.realm.RealmObject

/**
 * Created by songhyemin on 2017. 8. 24..
 */
public open class Achieve : RealmObject() {
    public open var sequence: Int? = null // 순서
    public open var categoryId: Int? = null // 시간 : 1, 레벨 : 2, 앱 사용 제약조건 : 3
    public open var title : String? = null // 업적 이름
    public open var purpose: Int? = null // 목표
    public open var state: Boolean? = null // 상태, 했는지 안했는지
}