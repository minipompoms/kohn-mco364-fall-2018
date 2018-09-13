package kohn.rx;
//observable, consumer, disposable(fmr. subscription)

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class RxSample {
    public static void main(String[] args) {

//        Observable<Boolean> observable = Observable.just(true, true, false, true);//creates an observable that emits just one value

       /* Disposable disposable = observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });*/

         Observable<Integer> observable = Observable.just(1,3,4,2,5,4,7,4);

        Disposable disposable = observable
                .filter(integer -> integer % 2 == 0)
                .map(integer -> integer * 2)
                .sorted()
                .distinct()
                .count()
                //.subscribeOn(Schedulers.computation())
                //.observeOn(Schedulers.single())
                .subscribe(System.out::println);

        //only dispose when finished.
        disposable.dispose();
    }
}
