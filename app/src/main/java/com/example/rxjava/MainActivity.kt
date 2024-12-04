package com.example.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*val observable = Observable.just(1, 2, 3f)
        val single = Single.just(1)
        val flowable = Flowable.just(1, 2, 3)

        val disposeObservable = observable.subscribe { element ->
            Log.e("MyTag", "$element")
        }

        val disposeSingle = single.subscribe { element ->
            Log.e("MyTag", "$element")
        }

        val disposeFlowable = flowable.subscribe { element ->
            Log.e("MyTag", "$element")
        }*/

        val button = findViewById<Button>(R.id.button)

        val disposeDataSource = dataSource()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ element ->
                button.text = "The number is $element"
                Log.e("MyTag", "$element")
            }, {

            }, {

            })
    }

    fun dataSource(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 0..100) {
                Thread.sleep(1000L)
                subscriber.onNext(i)
            }
        }
    }
}