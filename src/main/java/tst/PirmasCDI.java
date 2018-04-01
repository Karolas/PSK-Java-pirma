package tst;

import lombok.extern.slf4j.Slf4j;
import tst.mybatis.dao.TesttableMapper;
import tst.mybatis.model.Testtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
@Slf4j
public class PirmasCDI implements Serializable {
    public PirmasCDI() {
    }

    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString() + "\n" +
                "Letneje esanti eiluite: ";

    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}
