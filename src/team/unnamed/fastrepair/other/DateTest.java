package team.unnamed.fastrepair.other;

import java.util.Date;

/**
 * Created by cfwloader on 4/28/15.
 */
public class DateTest {

    public static void main(String[] args){
        Date date = new Date(System.currentTimeMillis());

        System.out.println(date.getTime());
    }
}
