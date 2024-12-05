package com.example.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Function
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.xml.validation.SchemaFactoryLoader
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {

    val disposeBag = CompositeDisposable()

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

       /* val disposeDataSource = dataSource()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ element ->
                button.text = "The number is $element"
                Log.e("MyTag", "$element")
            }*//*, {
                Log.e("MyTag", "ERROR CACHED")
            }*//*, {

            })*/

        /*val disposeSource = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("MyTag", "$result")
            },{

            })*/
/*
        val disposeSource = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("MyTag", "result success")
            }, {

            })*/


        /*val disposeSource = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("MyTag", "$it")
            }, {

            })*/

        // Поиск факториала с помощью функции .scan, которая имеет доступ к результату прошлых
        // вычислений (принцип рекурссии)
        /*val disposable = Observable.just(1, 2, 3, 4, 5, 6)
            .scan { t1, t2 ->
                t1 * t2
            }
            .subscribe {
                Log.e("MyTag", it.toString())
            }*/

        /*val timedObservable = Observable.just(1, 2, 3, 4, 5, 6)
            .zipWith(Observable.interval(0, 1000L, TimeUnit.MILLISECONDS),
                object : BiFunction<Int, Any, Int> {
                    override fun apply(t1: Int, t2: Any): Int {
                        return t1
                    }
                }
            )
            .sample(1500L, TimeUnit.MILLISECONDS)
            .subscribe({
                Log.e("MyTag", it.toString())
            }, {

            })*/

        val result = Observable.just("Леха", "Toha", "Toha", "Michel",
            "Vito", "Tom", "Sonny")
            /*.map {
                if (it.contains('o')) it + " Здоровый"
                else it + " Больной"
            }*/
            // Обрабатывает и возвращает элементы НЕ в том порядке, в котором они был заэмичнены
            // изначально, так как операции над ними выполняются параллельно и первым эмитится
            //  тот элемент, над которым flatMap первым закончил обработку
            /*.flatMap {
                val delay = Random.nextInt(10)
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }*/
            // Когда в него поступает очередной элемент, он сразу же завершает обработку старого,
            // из-за чего в данном случае он успеет обработать только последний переданный элемент
            // так как все элементы поступают в switchMap одновременно (созданы через just)
            /*.switchMap {
                val delay = Random.nextInt(10)
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }*/
            // Обрабатывает каждый элемент последовательно таким образом, что последовательно
            // сохраняется
            /*.concatMap {
                val delay = Random.nextInt(10)
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }*/
            //Собирает элементы в листы по кол-ву, равному числу в аргументе buffer и возвращает их
            /*.buffer(2)*/
            // Группирует элементы по какому-либо признаку и формирует из них объект типа
            // GroupedObservable, представлющий собой что-то похожее на map, который так же
            // сожно собрать методом subscribe
            /*.groupBy {
                it.contains('o')
            }*/
            // Когда отправляется очень много элементов, он отбрасывает те, которые не отвечают
            // указанному времени, а по истечении времени добавляет самый последний элемент
            /*.debounce(1, TimeUnit.SECONDS)*/
            // Отбра сывает дублирующиеся элементы. Если использовать классы, то лучше, чтобы у них
            // был переопределены методы equals() и hashCode(), так как иначе методу будет сравнивать
            // адреса элементов
            /*.distinct()*/
            // Тут ясно
            /*.filter(object : Predicate<String> {
                override fun test(t: String): Boolean {
                    return t.lowercase().contains('a')
                }
            })
            .filter{ it.lowercase().contains('o') }*/
            // Берет элемент на позиции, указанной в аргументе
            /*.elementAt(3)*/
            // Игнорирует все элементы, возвращает Completable, оповещающий об успешности или
            // неуспешности операции
            /*.ignoreElements()*/
            // По истечении времени передает дальше последий заэмиченный элемент, при этом не привязывается
            // к этим самым эмитам
           /* .sample(2, TimeUnit.SECONDS)*/
            // Пропускает и берет элементы с конца и с начала
            /*.skip(1)
            .skipLast(1)
            .take(1)
            .takeLast(1)*/
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })

            // для ignoreElements():
            /*.doOnComplete{
                Log.e("MyTag", "Completed")
            }*/
            // для groupBy:
            /*.subscribe({ result ->
                if (result.key == true) {
                    val disp = result
                        .subscribeOn(Schedulers.newThread())
                        .subscribe({
                            Log.e("MyTag", it)
                        }, {

                        })
                }
            }, {

            })
*/
        //disposeBag.add(result)

        /*Handler().postDelayed({
            Log.e("MyTag", "Disposed")
            result.dispose()
        }, 2000)*/
    }

    override fun onDestroy() {
        disposeBag.clear()
        super.onDestroy()
    }

    /*fun dataSource(): Flowable<Int> {
        return Flowable.create ({ subscriber ->
            for (i in 0..10000000) {
                subscriber.onNext(i)
            }

            subscriber.onComplete()
        }, BackpressureStrategy.ERROR)
    }*/

    /*fun dataSource(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 0..10000000) {
                subscriber.onNext(i)
            }

            subscriber.onComplete()
        }
    }*/

    /*fun dataSource(): Single<List<Int>> {
        return Single.create { subscriber ->
            subscriber.onSuccess(listOf(1, 2, 3, 4, 5))
        }
    }*/

    /*fun dataSource(): Completable {
        return Completable.create { subscriber ->
            subscriber.onComplete()
        }
    }*/

    fun dataSource(): Maybe<List<Int>> {
        return Maybe.create { subscriber ->
            subscriber.onSuccess(listOf(1, 2, 3, 4 ,5, 6, 7, 8))

            // will return only first element
            /*for (i in 1..100) {
                subscriber.onSuccess(i)
            }*/
            subscriber.onComplete()
        }
    }
}