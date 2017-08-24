package me.hyemdooly.sangs.dimigo.app.project.database

import io.realm.RealmObject
import java.util.*

/**
 * Created by songhyemin on 2017. 8. 23..
 */
public open class TimeUsed : RealmObject() {
    public open var time: Int? = null
    public open var date: Date? = null
}