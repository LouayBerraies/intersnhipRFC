package com.example.finale.service.jwt;

import com.example.finale.entities.Commande;
import com.example.finale.entities.Restaurant;
import com.example.finale.entities.User;
import com.example.finale.repository.CommandeRepository;
import com.example.finale.repository.RestaurantRepository;
import com.example.finale.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
@Service
public class CommandeServiceImpl implements CommandeService{

    @Autowired
    private CommandeRepository repository;
    @Autowired
    private  RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public void CommandeImpl(CommandeRepository commandeRepository, RestaurantRepository restaurantRepository) {
        this.repository = commandeRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Long saveCommande(Commande commande) {
        return repository.save(commande).getIdCommande();
    }



    @Override
    public List<Commande> getAllCommandes() {
        return (List<Commande>) repository.findAll();
    }

    @Override
    public Commande getCommandeById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteCommande(Integer sno) {
        repository.deleteById(sno);
    }
    @Override
    @Transactional
    public Commande addCommandeAndAffect(Commande commande, Integer restaurantId, Long userId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        User user = userRepository.findById(userId).orElse(null); // Assuming userRepository exists
        if (restaurant != null && user != null) {
            // Set user for the commande
            commande.setUser(user);
            // Associate the commande with the restaurant
            commande.setRestaurant(restaurant);
            return repository.save(commande);
        }
        return null;
    }

    @Override
    public int getCommandeCountForToday() {
        Date today = new Date();
        return (int) repository.findAll().stream()
                .filter(commande -> isSameDay(commande.getDateCommande(), today))
                .count();
    }

    @Override
    public int getCommandeCountForCurrentWeek() {
        Date weekStart = getStartOfWeek(new Date());
        return (int) repository.findAll().stream()
                .filter(commande -> commande.getDateCommande().after(weekStart))
                .count();
    }
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    private Date getStartOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }
    @Override
    public String getRestaurantNameForCommande(Integer commandeId) {
        Commande commande = repository.findById(commandeId).orElse(null);
        if (commande != null && commande.getRestaurant() != null) {
            Integer restaurantId = commande.getRestaurant().getIdRestaurant().intValue();
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
            if (restaurant != null) {
                return restaurant.getName();
            }
        }
        return null;
    }
    @Override
    public Commande rateOrder(Integer orderId, Integer rating) {
        Commande existingCommande = repository.findById(orderId).orElse(null);
        if (existingCommande != null) {
            existingCommande.setRating(rating);
            return repository.save(existingCommande);
        }
        return null;
    }
}
