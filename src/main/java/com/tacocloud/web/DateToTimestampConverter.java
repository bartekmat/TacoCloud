package com.tacocloud.web;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class DateToTimestampConverter {

          public static Timestamp getTimestamp(Date date) {
              return date == null ? null : new java.sql.Timestamp(date.getTime());
          }
}
