package spring.byDaniil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class VidokExample {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Config.class);
    }

    @Configuration
    public static class Config {
        @Bean(initMethod = "ohPleaseInitMe", destroyMethod = "ohPleaseKillMe")
        SuperClass newSuperClass(@Qualifier("hello") DuperClass duperClass, MuperClass muperClass) {
            return new SuperClass(duperClass, muperClass);
        }

        @Bean(name = "hello")
        DuperClass newDuperClass() {
            return new DuperClass("hello");
        }

        @Bean(name = "fuck")
        DuperClass anotherNewDuperClass() {
            return new DuperClass("fuck");
        }

        @Bean
        MuperClass newMuperClass(Optional<HuperClass> huperClass) {
            System.out.println("huperClass.isPresent " + huperClass.isPresent());
            return new MuperClass("good bye");
        }

        @PreDestroy
        public void goodBye() {
            System.out.println("good bye!!!111");
        }
    }

    public static class SuperClass implements InitializingBean, ApplicationContextAware {

        ApplicationContext applicationContext;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        public SuperClass(DuperClass duperClass, MuperClass muperClass) {
            System.out.println("SuperClass");
            System.out.println(duperClass);
            System.out.println(muperClass);
        }

        public void ohPleaseInitMe() {
            System.out.println("do init");
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        String s = bufferedReader.readLine();
                        System.out.println(s);
                        if (s.equals("die")) {
                            ((AbstractApplicationContext) applicationContext).destroy();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(false);
            thread.setName("reader thread");
            thread.start();
        }

        @PostConstruct
        public void ahaha() {
            System.out.println("do not do so");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("do not do so too");
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        public void ohPleaseKillMe() throws IOException {
            System.out.println("ohPleaseKillMe");
            bufferedReader.close();
        }
    }

    public static class DuperClass {

        private final String xxx;

        public DuperClass(String xxx) {
            this.xxx = xxx;
            System.out.println(toString() + " created");
        }

        @Override
        public String toString() {
            return "duper " + xxx;
        }
    }

    public static class MuperClass {

        private final String mmm;

        public MuperClass(String mmm) {
            this.mmm = mmm;
            System.out.println(toString() + " created");
        }

        @Override
        public String toString() {
            return "muper " + mmm;
        }
    }

    public static class HuperClass {
    }
}
