package kohn.rx.votesmart;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
                .map(feed -> feed.getCandidateList().getCandidate())
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.single())
                .subscribe(this::showCandidateData, throwable -> throwable.printStackTrace());
    }

    public void showCandidateData(List<Candidate> c){

      Stream<Candidate> list = c.stream().filter(c1  -> c1.getElectionYear().equals("2018"));
        viewProvider.get().setCandidates(list);
    }



    public void getBills() {
        disposable = service.getRecentBills()
                .flatMap(aLong -> service.getRecentBills())
                .map(feed -> feed.getBills().getBill())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::showBills, throwable -> throwable.printStackTrace());
    }

    private void showBills(List<Bill> list) {
        List<Bill> bills = list
                .stream()
                .collect(Collectors.toList());


        viewProvider.get().setBills(bills);

    }


    public void stop() {
        disposable.dispose();
    }


}
