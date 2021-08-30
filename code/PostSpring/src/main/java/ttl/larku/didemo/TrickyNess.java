package ttl.larku.didemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

interface Trick
{
    public void doTrick();
}

@Component
@Profile("dev")
//@Qualifier("us-east")
class Tricky1 implements Trick{
    @Override
    public void doTrick() {
        System.out.println("Somersault");
    }
}

@Component
@Order(2)
//@Primary
//@Qualifier("us-west")
@Profile("prod")
class Tricky2 implements Trick{
    @Override
    public void doTrick() {
        System.out.println("Hand Stand");
    }
}

@Component
@Order(1)
//@Primary
//@Qualifier("us-west")
@Profile("prod")
class Tricky3 implements Trick{
    @Override
    public void doTrick() {
        System.out.println("Card Trick");
    }
}

@Component
class Circus {

//    @Autowired()
//    @Qualifier("tricky1")
//    @Resource(name = "xzy")
    private Trick trick;

    @Autowired
    private List<Trick> tricks;

    public void startShow() {
//        trick.doTrick();
        //tricks.forEach(tr -> tr.doTrick());
        tricks.forEach(Trick::doTrick);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.scan("ttl.larku.didemo");
        context.refresh();

        Circus circus = context.getBean("circus", Circus.class);
        circus.startShow();

    }
}
