package org.decaywood.collector;

import org.decaywood.timeWaitingStrategy.TimeWaitingStrategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: decaywood
 * @date: 2015/11/27 0:27
 */


public class DateRangeCollector extends AbstractCollector <List<Date>> {

    private final Date from;
    private final Date to;

    public DateRangeCollector(Date from, Date to) {
        this(null, from, to);
    }

    public DateRangeCollector(TimeWaitingStrategy strategy, Date from, Date to) {
        super(strategy);
        if(from == null || to == null || from.after(to)) throw new IllegalArgumentException();
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Date> collectLogic() throws Exception {

        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder();

        for (Date i = from; i.before(to) || i.equals(to); ) {

            dates.add(i);

            calendar.setTime(i);
            builder.delete(0, builder.length());
            calendar.add(Calendar.DATE, 1);
            i = calendar.getTime();

        }

        return dates;

    }

}
