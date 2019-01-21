package kohn.rx.votesmart;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VoteSmartService {

	static final String APIKEY = "4621bd0bc679f84d6eee42c99c643e57";

	@GET("/Candidates.getByZip?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getCandidatesbyZipCode(
			@Query("zip5") String zip5,
			@Query("zip4") String zip4);
	

	@GET("/State.getStateIDs?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getStateIDs();


	@GET("/Votes.getBillsByStateRecent?&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getRecentBills();


	@GET("/Votes.getBillsByYearState?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getBillsByState(
			@Query("year") String year,
			@Query("stateId") String stateId);

	
/*	@GET("/Votes.getBillsByStateRecent?&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getRecentBills(
			@Query("amount") String amount);*/
	
	@GET("/Election.getElectionByZip?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getElectionByZip(
			@Query("zip5") String zip5,
			@Query("zip4") String zip4);

	@GET("/Officials.getStatewide?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getStateOfficials(
			@Query("stateId") String stateId);

	@GET("Office.getTypes?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getOfficeTypes();

	@GET("Rating.getCategories?/&o=JSON&key=" + APIKEY)
	Observable<VoteSmartFeed> getCategories();

}

