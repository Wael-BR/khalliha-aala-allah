package tn.esprit.spring.gestionstationski.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.gestionstationski.entities.Subscription;
import tn.esprit.spring.gestionstationski.entities.TypeSubscription;

public interface ISubscriptionServices {

	Subscription addSubscription(Subscription subscription);

	Subscription updateSubscription(Subscription subscription);

	Subscription retrieveSubscriptionById(Long numSubscription);

	Set<Subscription> getSubscriptionByType(TypeSubscription type);

	List<Subscription> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate);

	void retrieveSubscriptions();
}
