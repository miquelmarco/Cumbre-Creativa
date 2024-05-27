package cumbrecreativa.cumbrecreativa;

import cumbrecreativa.cumbrecreativa.models.*;
import cumbrecreativa.cumbrecreativa.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class CumbrecreativaApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(CumbrecreativaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CustomerService customerService, EventService eventService, AssistanceService assistanceService, CommentService commentService, LocationService locationService, RatingService ratingService) {
        return args -> {

//            test customers

            Customer testCustomer = new Customer("testUser", "testUser", "testUser", LocalDate.of(1987, 4, 8), Gender.NOTSPECIFY, Rol.ADMIN, "lala", true, "miquel.marco@outlook.com", passwordEncoder.encode("123456"));
            Customer organCustomer = new Customer("organUser", "organUser", "CumbreCreativa", LocalDate.of(1987, 4, 8), Gender.NOTSPECIFY, Rol.ORGANIZER, "lalala", true, "miquel.marco.01@gmail.com", passwordEncoder.encode("123456"));

            customerService.save(testCustomer);
            customerService.save(organCustomer);

//            test locations

            Location testLocation1 = new Location("Centro de Eventos Laura Dern", "No32, Calle Las Gracias", "Santiago", "Chile", "-33.45694, -70.64827");
            Location testLocation2 = new Location("Plaza Maipú", "No54, Calle Lira", "Maipú", "Chile", "33°30′35″S 70°45′23″O");
            Location testLocation3 = new Location("Biblioteca Nacional", "Av. Pajaritos con Av. 5 de Abril", "Santiago", "Chile", "33°26′32″S 70°38′44″O");
            Location testLocation4 = new Location("Teatro Ceina", "Arturo Prat 33 Metro U. de Chile", "Santiago", "Chile", "33°26′42″S 70°39′01″O");
            Location testLocation5 = new Location("Estadio Nacional", "Parque Deportivo Estadio Nacional", "Ñuñoa", "Chile", "33°27′52″S 70°36′38″O");

            locationService.save(testLocation1);
            locationService.save(testLocation2);
            locationService.save(testLocation3);
            locationService.save(testLocation4);
            locationService.save(testLocation5);

//            test events

            Event newEvent1 = new Event("Encuentro Literario", "Cumbre Creativa", "En el marco del Mes del Libro y los 30 años que cumple la Corporación Cultural de Lo Barnechea (CCLB), el autor de libros sobre divulgación científica, Andrés Gomberoff protagonizará un “encuentro literario” con los vecinos de la comuna. Gomberoff, escritor y doctor en física, conversará sobre su última publicación “El instinto científico”, libro que se embarca en un lúdico viaje por los orígenes y fundamentos del pensamiento científico.", "https://www.duna.cl/media/2012/07/Andr%C3%A9s-Gomberoff.jpg", LocalDate.now().plusMonths(3), LocalTime.now().plusHours(3), (byte) 4, true);
            Event newEvent2 = new Event("Pedro Capó en Concierto", "Cumbre Creativa", "Sus seguidores podrán disfrutar del talento de Capó, sus hits como Calma (canción que supera los mil millones de reproducciones en el mundo) y los nuevos singles de su disco “La Neta”. Sencillos como “Hoy me siento cabrón”, “Ni tan Novios ni tan amigos” y “5 y 3”, donde aborda diversos géneros musicales entre los que cuentan el pop, la balada, el reggaetón y el rock.", "https://static.ptocdn.net/especiales/biz266_pedro-capo/img/portada-resena-min.jpg", LocalDate.now().plusMonths(3), LocalTime.now().plusHours(3), (byte) 4, true);
            Event newEvent3 = new Event("Juan Pablo López, Tay Wando", "Cumbre Creativa", "Nuevo show de stand up comedy de Juan Pablo López. Una rutina que habla del mundo actual, del cual estamos viviendo, cargada de risas y momentos inolvidables.", "https://static.ptocdn.net/images/eventos/lop001v2_calugalistado.jpg", LocalDate.now().plusMonths(4), LocalTime.now().plusHours(3), (byte) 3, true);
            Event newEvent4 = new Event("Sigrid en Teatro", "Cumbre Creativa", "Escribiendo enormes canciones pop desde el corazón, con ese timbre raspado y recordando a grandes vocalistas pasando por Stevie Nicks hasta Carole King y Freddie Mercury, desde su ascensión de Sigrid al estrellato pop en 2017, la joven de 27 años ha consolidado su posición como una de las voces que hay que escuchar en el alt-pop.", "https://static.ptocdn.net/especiales/fna307_sigrid-teatro-coliseo-2024/img/foto_sigrid.png", LocalDate.now().plusMonths(1), LocalTime.now().plusHours(1), (byte) 5, true);
            Event newEvent5 = new Event("Premier Padel", "Cumbre Creativa", "Toda una semana del mayor evento deportivo de padel del país, ven a disfrutar de una tarde de competiciones en familia!", "https://static.ptocdn.net/especiales/act002_premier-padel-2024/img/portada-xl.jpg?=ddd", LocalDate.now().plusMonths(2), LocalTime.now().plusHours(3), (byte) 0, true);
            Event newEvent6 = new Event("Edo Caroe, Peligrosamente Bien", "Cumbre Creativa", "Edo Caroe presenta una versión ligera y distendida de su exitosa rutina Peligrosamente Bien, donde afronta su pasado, presente y futuro con su mejor arma: los chistes", "https://static.ptocdn.net/images/eventos/edo037v2_calugalistado.jpg", LocalDate.now().plusMonths(4), LocalTime.now().plusHours(5), (byte) 4, true);
            Event newEvent7 = new Event("Ciclo Royal Opera House", "Cumbre Creativa", "Transmición del ciclo de Opera y Ballet desde Londres, en la pantalla del Teatro Oriente", "https://static.ptocdn.net/images/eventos/fcp143_calugalistado.jpg", LocalDate.now().plusMonths(1), LocalTime.now().plusHours(3), (byte) 5, true);
            Event newEvent8 = new Event("Sesiones Híbridas 1 - Sesión Urbana", "Cumbre Creativa", "La primera noche de encuentro en torno a las danzas que el programa HÍBRIDA y CEINA propone, viene cargada de procesos creativos, música y performance en torno a la investigación en las danzas urbanas y sociales actuales. Acompáñanos a descubrir que están moviendo y pensando distintos creadores en torno a los lenguajes y las danzas.", "https://static.ptocdn.net/images/eventos/cee118_calugalistado.jpg", LocalDate.now().plusMonths(6), LocalTime.now().plusHours(4), (byte) 2, true);
            Event newEvent9 = new Event("Hermanos Ilabaca", "Cumbre Creativa", "Primer concierto de los “Hermanos Ilabaca” donde presentaran en su totalidad, su disco debut “HI”", "https://static.ptocdn.net/images/eventos/moj005_calugalistado.jpg", LocalDate.now().plusMonths(2), LocalTime.now().plusHours(1), (byte) 4, true);
            Event newEvent10 = new Event("Un Espejo, Teatro", "Cumbre Creativa", "Con mucho gusto, Leyla y Joel les invitan a celebrar su matrimonio. Tras la apertura de las puertas, se procederá al intercambio de votos. Y a la señal, comienza la entretención. Este espectáculo se realiza sin una licencia del Ministerio. Reconocemos el riesgo que cada uno de ustedes asume al asistir, y saludamos su valentía. Una boda, una obra de teatro, una mentira.", "https://static.ptocdn.net/images/eventos/zoc044v4_calugalistado.jpg", LocalDate.now().plusMonths(1), LocalTime.now().plusHours(2), (byte) 3, true);
            Event newEvent11 = new Event("Expo Tattoo Chile", "Cumbre Creativa", "La competencia de tatuaje más grande de la cuarta región, con una fusión oriental, sumando competencia de cosplay y dance cover. Con concierto en vivo y stand up comedy, además de contar con un patio de comida, con food truck y cervezas artesanales, una zona gamer con simuladores de f1 y totem de PS5 además de consolas retro, gratuitas dentro del evento.", "https://static.ptocdn.net/images/eventos/oku001_calugalistado.jpg", LocalDate.now().plusMonths(5), LocalTime.now().plusHours(2), (byte) 4, true);

            organCustomer.addEvent(newEvent1);
            organCustomer.addEvent(newEvent2);
            organCustomer.addEvent(newEvent3);
            organCustomer.addEvent(newEvent4);
            organCustomer.addEvent(newEvent5);
            organCustomer.addEvent(newEvent6);
            organCustomer.addEvent(newEvent7);
            organCustomer.addEvent(newEvent8);
            organCustomer.addEvent(newEvent9);
            organCustomer.addEvent(newEvent10);
            organCustomer.addEvent(newEvent11);
            testLocation3.addEvent(newEvent1);
            testLocation2.addEvent(newEvent2);
            testLocation4.addEvent(newEvent3);
            testLocation4.addEvent(newEvent4);
            testLocation5.addEvent(newEvent5);
            testLocation4.addEvent(newEvent6);
            testLocation4.addEvent(newEvent7);
            testLocation1.addEvent(newEvent8);
            testLocation4.addEvent(newEvent9);
            testLocation4.addEvent(newEvent10);
            testLocation1.addEvent(newEvent11);

            eventService.save(newEvent1);
            eventService.save(newEvent2);
            eventService.save(newEvent3);
            eventService.save(newEvent4);
            eventService.save(newEvent5);
            eventService.save(newEvent6);
            eventService.save(newEvent7);
            eventService.save(newEvent8);
            eventService.save(newEvent9);
            eventService.save(newEvent10);
            eventService.save(newEvent11);
        };
    }
}