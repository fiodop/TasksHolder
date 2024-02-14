package Taskt.aspects;

import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
@Aspect

public class LoggingAspect {
    @Pointcut("execution(* Taskt.Services.TaskService.*())")
    public void beforeAllMethodsAdvice(JoinPoint joinPoint){
        String log = "beforeAllMethodsAdvice: ";
        FileWriter writer = null;
        try {
            writer = new FileWriter("C:\\Users\\artem\\IdeaProjects\\TasksHolder", true);
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            writer.write(log + methodSignature.getMethod());
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

    }

}
