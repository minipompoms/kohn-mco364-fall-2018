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
/*


    public void refreshCatagories(){
        disposable = Observable.interval(0,10, TimeUnit.SECONDS)
                .flatMap(aLong -> service.getRecentBills())
                .map(feed -> feed.getBillsList())
                .subscribeOn(Schedulers.io())
                .subscribe(this::setBills, throwable -> throwable.printStackTrace());
    }

*/


    public void loadStates(){
        disposable = Observable.interval(0,100, TimeUnit.DAYS)
                .flatMap(aLong -> service.getStateIDs())
                .map(feed -> feed.getStateList())
                .subscribeOn(Schedulers.io())
                .subscribe(this::setStateList, throwable -> throwable.printStackTrace());
    }

    private void setStateList(StateList list){
	    List<InternalList.State> states = list
                .getList()
                .getState()
                .stream()
                .limit(55)
                .collect(Collectors.toList());
               viewProvider.get().setStates(states);
    }
   /* private void setBills(List<Bill> list){
       List<Bill> bills = list
               .stream()
               .filter(bill -> bill.getBillNumber().startsWith("2"))
               .collect(Collectors.toList());
        viewProvider.get().setData(bills);

    }
*/
    public void stop() {
        disposable.dispose();
    }

	

}
