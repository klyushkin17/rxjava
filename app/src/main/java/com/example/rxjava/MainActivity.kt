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
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.xml.validation.SchemaFactoryLoader
import kotlin.random.Random as Random

const val TAG = "MyTag"

class MainActivity : AppCompatActivity() {

    val disposeBag = CompositeDisposable()

    @SuppressLint("MissingInflatedId", "CheckResult")
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
            .map {
                if (it.contains('o')) it + " Здоровый"
                else it + " Больной"
            }
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
            /*.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })*/

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

        val names = Observable.just("Леха", "Toha", "Michel",
            "Vito", "Tom", "Sonny")
        val surnames = Observable.just("Donchich", "Stalune",
            "Bebricks", "Carleone", "Vivaldi", "Ivanov", "Peppa")

        val temperatureFactoryFirst = Observable.just(120, 11, 90,
            100, 102)
        val temperatureFactorySecond = Observable.just(-10, -20,
            -5, -30)

        val left = Observable
            .interval(100, TimeUnit.MILLISECONDS)

        val right = Observable
            .interval(300, TimeUnit.MILLISECONDS)

        // do-операции исполняются ДО их аналого без do
        /*names.doOnNext {} // Используется в основном для логирования до того, как был вызван
            // , например, оператор filter()
            .doOnSubscribe {}
            .doOnComplete {} // Вызывает, когда поток завершил свою работу
            .doOnError {  }
            .filter { it.length > 5}
            .subscribe({

            }, {

            })*/

        //Выбрасывает TimeoutException, если поток не успел отработать за определенное время
        /*names.timeout (300, TimeUnit.MILLISECONDS)
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })*/


        // Определеяет, в какое время было заэмичено значение
        /*names.timestamp()
            .subscribe({
                Log.e("MyTag", "time -> ${it.time()} value -> ${it.value()}")
            }, {

            })*/

        // Определяет, с каким временным интервалом были получены значения.
       /* names.timeInterval()
            .subscribe({
                Log.e("MyTag", "time -> ${it.time()} value -> ${it.value()}")
            }, {

            })*/

        // Предоставляет значение по умолчанию, если не пришло ни одного элемента из потока
        /*names.skip(6)
            .defaultIfEmpty("Nothing found")
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })*/

            //.count() // - считает количество определенных элементов в потоке

        // В данном случае элементы выведутся с задержкой
        /*names.delay(3, TimeUnit.SECONDS)
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })*/

        // Проверяет, содержит ли поток определенный элемент
        /*names.contains("Toha")
            .subscribe({
                Log.e("MyTag", it.toString())
            }, {

            })*/

        // Проверяет, удовлетворяют ли ВСЕ данные из нашего потока определенному условию, при этом
        // возвращая Single
        /*names.all { it.length > 1 }
            .subscribe({
                Log.e("MyTag", it.toString())
            }, {

            })*/

        // Объединяет два потока в один, прим это комбинирует элементы этих потоков
        /*left.join(right,
            { t -> Observable.timer(300, TimeUnit.MILLISECONDS) },
            { t -> Observable.timer(100, TimeUnit.MILLISECONDS) },
            { t1, t2 ->
                Log.e("MyTag", "left $t1 right $t2")
                t1 + t2
            }
        ).take(10)
            .subscribe({
                Log.e("MyTag", "Result -> $it")
            }, {
                Log.e("MyTag", "error $it")
            })*/


        // switchOnNext() - в теории при объединении потока, по истечении определенного промежутка
        // времени, прекращает исполнение первого потока и переключается на следующий

        // Объединяет два потока, причем сначала выводит элементы первого потока, затем элементы
        // второго, при этом сохраняя последовательность
       /* names.concatWith(surnames)
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })*/

        // Объединяет несколько потоков в один, при этом каждый раз выводит самую актуальную
        // информацию, то есть изменяет объединенный поток, когда какой-то из объединенных потоков
        // обновляет информацию
        /*Observable.combineLatest(temperatureFactoryFirst.zipWith(Observable.interval(300L,
            TimeUnit.MILLISECONDS)){ t1, _ -> t1},
            temperatureFactorySecond.zipWith(Observable.interval(500L, TimeUnit.MILLISECONDS))
            { t1, _ -> t1}
        ) { t1, t2 -> arrayOf(t1, t2) }
            .subscribe({
                Log.e("MyTag", "factory one - ${it[0]}, factory two - ${it[1]}")
            }, {

            })*/

        // Так же объеиняет два потока, но при этом не заботаться о том, чтобы дожидаться элементы,
        // эмитит их в новый поток по мере возможности, тем самым, в отличие от zip, у нас нет
        // возможности прописывать логику объединяния двух элементов из разных потоков
        /*names.zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS)){t1, _ -> t1}
            .mergeWith(surnames.zipWith(Observable.interval(500L, TimeUnit.MILLISECONDS)){t1, _ -> t1})
            .subscribe({
                Log.e("MyTag", it)
            }, {

            })*/

        // Объединяет несколько потоков в один и выполняет какие-либо действия с ними
        // В его рамках мы имеем доступ к одному элементу из каждого потока. Если для какого-либо
        // элемента не нашлось пары, то он опускается
        /*names.zipWith(surnames, object : BiFunction<String, String, String> {
            override fun apply(t1: String, t2: String): String {
                return "$t1 $t2"
            }
        })
        .zipWith(surnames) { t1, t2 ->
            "$t1 $t2"
        }
        .subscribe({
            Log.e("MyTag", it)
        }, {

        })*/


        // Introduction to Subject
        /*val source1 = Observable.interval(1, TimeUnit.SECONDS).map {
            it + 1
        }
        val source2 = Observable.interval(300, TimeUnit.MILLISECONDS).map {
            (it + 1) * 300
        }

        val subject = PublishSubject.create<Long>()

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {

        })

        source1.subscribe(subject)
        source2.subscribe(subject)*/


        // Таким макаром данные не соберутся, так как Subject -- горячий поток данных, а это означает
        // что в нашем случае, данные сначала заемитятся, а толоко поток начнется их сбор
        /*val subject = PublishSubject.create<Int>()

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(4)
        subject.onComplete()

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})*/


        // Таким макаром, с помощью behavior, мы позволяем новому подписчику получить последнее
        // заэмиченное значение
        /*val subject = BehaviorSubject.create<Int>()

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(4)

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})*/

        // Таким вот макаром мы кеширует ВСЕ данные и новые подписчики их с кайфом получают
       /* val subject = ReplaySubject.create<Int>()

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(4)

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})*/

        // Вот таким макаром данные передаются только после вызова onComplete, причем только
        // самое послденее значение
        /*val subject = AsyncSubject.create<Int>()

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(4)
        subject.onComplete()*/

        /*val subject = AsyncSubject.create<Int>()

        subject.subscribe({
            Log.e("MyTag", it.toString())
        }, {})

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(4)
        subject.onComplete()*/


        // Если вдруг в потке возникла ошибка, то оператор даст шанс попробовать заэмитить данные еще раз
        // Если в какой-то момент поток отработает без ошибок, он так же передаст неудачные попытки
        getData()
            .retry(3)
            .subscribe({
                Log.e(TAG, "Get value $it")
            }, {
                Log.e(TAG, "Error handled!!! $it")
            })

        // onErrorReturn -- позволяет поймать ошибку и вернуть какое-либо значине, которое потом попадет
        // подписчику.
       /* getData()
            .onErrorReturn {
                if (it is IllegalArgumentException) {
                    12
                } else 13
            }
            .subscribe({
                Log.e(TAG, "Get value $it")
            }, {
                Log.e(TAG, "Error handled!!! $it")
            })*/


        // onErrorResumeNext -- Позволяет создать новый observable, который будет собираться
        // получателем при возникновении ошибки. При этом ошибка не будет попадать в onError блок
        // подписчика. Но если в новосозданном observable выбросится ошибка, то она все же прокинется получателю
        /*getData()
            .onErrorResumeNext {
                Observable.create{ subscriber ->
                    subscriber.onNext(1)
                    subscriber.onNext(2)
                    subscriber.onNext(3)
                    throw IllegalArgumentException()
                }
            }
            .subscribe({
                Log.e(TAG, "Get value $it")
        }, {
            Log.e(TAG, "Error handled!!! $it")
        })*/
    }

    // Для проверки retry()
    fun getData(): Observable<Int> {
        return Observable.create { subscriber ->
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            list.forEach { element ->
                subscriber.onNext(element)
                try {
                    if (element == 8 && System.currentTimeMillis() % 2 == 0L) {
                        //throw IllegalArgumentException()
                        subscriber.onError(IllegalArgumentException())
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error is handled in try block")
                }
            }
        }
    }
    // Выводы:
    // Если отловить ошибку в try блоке, то она не прокинется получателю

   /* fun getData(): Observable<Int> {
        return Observable.create { subscriber ->
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            list.forEach { element ->
                subscriber.onNext(element)
                try {
                    if (element > 4) {
                        //throw IllegalArgumentException()
                        subscriber.onError(IllegalArgumentException())
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error is handled in try block")
                }
            }
        }
    }*/

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

    /*fun dataSource(): Maybe<List<Int>> {
        return Maybe.create { subscriber ->
            subscriber.onSuccess(listOf(1, 2, 3, 4 ,5, 6, 7, 8))

            // will return only first element
            *//*for (i in 1..100) {
                subscriber.onSuccess(i)
            }*//*
            subscriber.onComplete()
        }
    }*/

}