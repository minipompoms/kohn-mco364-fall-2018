package kohn.rx.votesmart;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class VoteSmartController {

    private Provider<VoteSmartView> viewProvider;
    private VoteSmartService service;
    private Disposable disposable;

    @Inject
    public VoteSmartController(VoteSmartService service, Provider<VoteSmartView> viewProvider) {
        this.viewProvider = viewProvider;
        this.service = service;
    }

    public void loadStates() {
        disposable = Observable.interval(0, 30, TimeUnit.SECONDS)
                .flatMap(aLong -> service.getStateIDs())
                .map(feed -> feed.getStateList())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::setStateList, throwable -> throwable.printStackTrace());
    }

    private void setStateList(StateList list) {
        List<InternalList.State> states = list
                .getList()
                .getState()
                .stream()
                .filter((state -> state.getStateId().startsWith("N")))
                .limit(10)
                .collect(Collectors.toList());
        viewProvider.get().setStates(states);
    }

    public void refreshBills() {
        disposable = Observable.interval(0, 10, TimeUnit.SECONDS)
                .flatMap(aLong -> service.getRecentBills())
                .map(feed -> feed.getBills().getBill())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::setBills, throwable -> throwable.printStackTrace());
    }


    public void refreshElections() {

        disposable = Observable.interval(0, 10, TimeUnit.SECONDS)
                .flatMap(aLong -> service.getElectionByZip("11230", "3341"))
                .map(feed -> feed.getElections().getElection())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::setElections, throwable -> throwable.printStackTrace());
    }

    private void setElections(List<Elections.Election> list) {
       System.out.println(list.get(0));

        List<Elections.Election> elections = list
                .stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(elections.get(0));

        viewProvider.get().setElections(elections);

    }


    private void setBills(List<Bill> list) {
        List<Bill> bills = list
                .stream()
                .filter(bill -> bill.getBillNumber().startsWith("2"))
                .collect(Collectors.toList());


        viewProvider.get().setBills(bills);

    }


    public void stop() {
        disposable.dispose();
    }


}
