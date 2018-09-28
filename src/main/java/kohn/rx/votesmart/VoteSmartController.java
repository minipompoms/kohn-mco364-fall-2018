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

    public void getStateList() {
        disposable = service.getStateIDs()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::showStateList, throwable -> throwable.printStackTrace());
    }

    private void showStateList(VoteSmartFeed feed) {
      InternalList list = feed.getStateList().getList();
      viewProvider.get().setStates(list);
    }

    public void getElectionData() {
        disposable = service.getElectionByZip("11230", "3341")
                .map(feed -> feed.getElections())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::showElectionData, throwable -> throwable.printStackTrace());
    }

    private void showElectionData(Elections e) {
        List<Elections> list = e.getElection();
        viewProvider.get().setElections(list);
    }


    public void getCandidateData(){

        disposable = service.getCandidatesbyZipCode("11230", "3341")
                .map(feed -> feed.getCandidateList())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::showCandidateData, throwable -> throwable.printStackTrace());
    }

    public void showCandidateData(CandidateList c){
        List<Candidate> list = c.getCandidate();
        viewProvider.get().setCandidates(list);
    }



    public void getBills() {
        disposable = Observable.interval(0, 10, TimeUnit.SECONDS)
                .flatMap(aLong -> service.getRecentBills())
                .map(feed -> feed.getBills().getBill())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::setBills, throwable -> throwable.printStackTrace());
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
