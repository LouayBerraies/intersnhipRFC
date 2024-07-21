package com.example.finale.service.jwt;

import com.example.finale.entities.*;
import com.example.finale.repository.ReservationRepository;
import com.example.finale.repository.RessourceRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RessourceRepository ressourceRepository;



    @Override
    public Long saveReservation(Reservation reservation) {
        return repository.save(reservation).getIdReservation();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) repository.findAll();
    }

    @Override
    public Reservation getReservationById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteReservation(Integer sno) {
        repository.deleteById(sno);
    }
    @Override
    public Integer reservationsEnAttente() {
        List<Reservation> rs = repository.findAll();
        Integer d = 0;
        for (Reservation r : rs) {
            if (r.getStatus().equals(ReservationStatus.EN_ATTENTE)) {
                d++;
            }
        }
        return d;
    }
    @Transactional
    public Reservation addReservationAndAffect(Reservation reservation, Integer ressourceId, Long userId) {
        Reservation r = repository.save(reservation);
        Ressource rs = ressourceRepository.findById(ressourceId).get();

        r.setRessource(rs);
        r.setStatus(ReservationStatus.EN_ATTENTE);

        // Set user ID
        User m = new User();
        r.setUser(m);
        r.getUser().setId(userId);

        rs.setStatus(RessourceStatus.Indisponible);

        if (rs.getNbR() != null) {
            rs.setNbR(rs.getNbR()+1);
        } else {
            rs.setNbR(1);
        }

        repository.save(r);
        ressourceRepository.save(rs);

        return r;
    }

    @Override
    public Reservation refuserReservation(Integer idR){
        Reservation r = repository.findById(idR).get();
        r.setStatus(ReservationStatus.REFUSEE);
        repository.save(r);
        return r;
    }
    @Override
    public Reservation acceptReservation(Integer idR) throws MessagingException {
        Reservation r = repository.findById(idR).get();
        r.setStatus(ReservationStatus.ACCEPTEE);
        repository.save(r);
        System.out.println("E-mail de l'utilisateur accepté : " + r.getUser().getEmail());
        sendConfirmationEmail(r.getUser().getEmail()); // Assuming client email is stored in r

        return r;
    }
    @Override
    public Integer totalReservations() {
        List<Reservation> rs = repository.findAll();
        return rs.size();
    }
    @Autowired
   private  JavaMailSender mailSender;

    @Override
    public void sendConfirmationEmail(String toEmail) throws MessagingException, jakarta.mail.MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);



        String emailContent = "<html><body>" +
                "<p>Cher/Chère Etudiant,</p>" +
                "<br>" +
                "<p>J'espère que ce courriel vous trouve bien. Je suis ravie de vous confirmer votre réservation et de vous accueillir prochainement !</p>" +
                "<br>" +

                "<p>Nous mettrons tout en œuvre pour vous offrir un séjour inoubliable. Si vous avez des questions supplémentaires ou des demandes particulières, n'hésitez pas à nous contacter.</p>" +
                "<br>" +
                "<p>Nous avons hâte de vous accueillir et de vous offrir une expérience exceptionnelle. Si vous avez besoin d'une assistance supplémentaire, veuillez nous contacter à l'adresse e-mail suivante : yasmine.maazoun@esprit.tn.</p>" +
                "<br>" +
                "<p>Cordialement,</p>" +
                "<br>" +
                "<p>L'equipe Administration</p>" +
                "<p>Cordialement,</p>" +
                "<p>Remarque : Ce message a été envoyé automatiquement. Veuillez ne pas y répondre directement.</p>" +
                "</body></html>";

        helper.setFrom("maher.karoui@esprit.tn");
        helper.setTo(toEmail);
        helper.setSubject("Confirmation de votre réservation");
        helper.setText(emailContent, true);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }



}
