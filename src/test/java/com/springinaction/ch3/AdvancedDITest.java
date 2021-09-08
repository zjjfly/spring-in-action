package com.springinaction.ch3;

import com.springinaction.ch2.BlankDisc;
import com.springinaction.common.AbstractTest;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by zjjfly on 2016/12/20.
 */
@ActiveProfiles("dev")
public class AdvancedDITest extends AbstractTest{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MagicBean magicBean;

    @Autowired
    @Cold
    @Creamy
    private Dessert dessert;

    @Autowired
    @Cold
    @Fruity
    private Dessert another;

    @Autowired
    private Notepad notepad1;

    @Autowired
    private Notepad notepad2;

    @Autowired
    @Qualifier("disc")
    BlankDisc disc;

    @Autowired
    @Qualifier("blankdisc")
    BlankDisc blankDisc;

    @Autowired
    @Qualifier("blank")
    BlankDisc blank;

    @Test
    public void dataSource() throws Exception {
        assertTrue(dataSource instanceof BasicDataSource);
    }

    @Test
    public void magicBean() throws Exception {
        assertNotNull(magicBean);
    }

    @Test
    public void dessert() throws Exception {
        assertTrue(dessert instanceof IceCream);
    }

    @Test
    public void otherDessert() throws Exception {
        assertTrue(another instanceof Popsicle);
    }

    @Test
    public void scopeTest() throws Exception {
        assertTrue(notepad1!=notepad2);
    }

    @Test
    public void discTest(){
        System.out.println(disc);
    }

    @Test
    public void placeHolderTest(){
        System.out.println(blankDisc);
    }

    @Test
    public void spelTest(){
        System.out.println(blank);
    }
}
