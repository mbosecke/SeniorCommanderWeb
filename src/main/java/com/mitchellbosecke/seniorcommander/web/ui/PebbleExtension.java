package com.mitchellbosecke.seniorcommander.web.ui;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.escaper.SafeString;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mitch_000 on 2016-10-22.
 */
public class PebbleExtension extends AbstractExtension {

    @Override
    public Map<String, Filter> getFilters() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("utc", new UtcFilter());
        return filters;
    }

    public static class UtcFilter implements Filter {

        @Override
        public SafeString apply(Object input, Map<String, Object> args) {

            if (input == null) {
                return null;
            } else {
                String format = (String)args.get("format");
                ZonedDateTime date = (ZonedDateTime)input;
                String utc = date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                String span = String.format("<span class=\"utc-date\" data-format=\"%s\">%s</span>", format, utc);
                return new SafeString(span);
            }
        }

        @Override
        public List<String> getArgumentNames() {
            List<String> args = new ArrayList<>();
            args.add("format");
            return args;
        }
    }
}
