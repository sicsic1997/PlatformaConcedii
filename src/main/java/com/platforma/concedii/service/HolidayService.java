package com.platforma.concedii.service;

import com.platforma.concedii.dao.HolidayDAO;
import com.platforma.concedii.domain.HolidayFilter;
import com.platforma.concedii.dto.HolidayDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HolidayService {

    private static final String SAVE_HOLIDAY_SUCCESS = "Holiday request registered with success";
    private static final String SAVE_HOLIDAY_OVERLAPS = "The request overlaps with employee requests from: ";
    private static final HolidayService holidayServiceInstance = new HolidayService();
    private HolidayService(){};
    public static HolidayService getInstance() {
        return holidayServiceInstance;
    }

    /**/
    public String saveHoliday(HolidayDTO holidayDTO) {
        int nrOverlapping = 0;
        List<String> overlappingDatesToString = new ArrayList<>();
        List<HolidayDTO> holidayDTOList = HolidayDAO.getInstance().getAllHolidaysForUserIfSpecified(holidayDTO.getUserId());
        for (HolidayDTO holiday: holidayDTOList) {
            Date minStartDate = holiday.getStartDate().before(holidayDTO.getStartDate()) ?
                    holiday.getStartDate() : holidayDTO.getStartDate();
            Date maxEndDate = holiday.getEndDate().before(holidayDTO.getEndDate()) ?
                    holidayDTO.getEndDate() : holiday.getEndDate();

            Long diffTotal = maxEndDate.getTime() - minStartDate.getTime();
            Long diff1 = holiday.getEndDate().getTime() - holiday.getStartDate().getTime();
            Long diff2 = holidayDTO.getEndDate().getTime() - holidayDTO.getStartDate().getTime();

            long timeInDaysTotalRange = TimeUnit.DAYS.convert(diffTotal, TimeUnit.MILLISECONDS);
            long time1 = TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS);
            long time2 = TimeUnit.DAYS.convert(diff2, TimeUnit.MILLISECONDS);

            if(timeInDaysTotalRange < time1 + time2) {
                nrOverlapping = nrOverlapping + 1;
                SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                String message = myFormat.format(holiday.getStartDate()) + " to " + myFormat.format(holiday.getEndDate());
                overlappingDatesToString.add(message);
            }
        }
        if(nrOverlapping == 0) {
            return SAVE_HOLIDAY_SUCCESS;
        } else {
            return SAVE_HOLIDAY_OVERLAPS + String.join(" and ", overlappingDatesToString);
        }
    }

    /**/
    public List<HolidayDTO> getAllHolidaysByFilter(HolidayFilter holidayFilter) {

        List<HolidayDTO> holidayDTOList = HolidayDAO.getInstance().getAllHolidaysForUserIfSpecified(0);

        //Apply DateRange Filter if date-range is set
        if(holidayFilter.isUseDateRange()) {
            List<HolidayDTO> auxHolidayDTOList = new ArrayList<>(holidayDTOList);
            holidayDTOList = new ArrayList<>();

            for (HolidayDTO holiday:auxHolidayDTOList) {
                Date minStartDate = holiday.getStartDate().before(holidayFilter.getMinDate()) ?
                        holiday.getStartDate() : holidayFilter.getMinDate();
                Date maxEndDate = holiday.getEndDate().before(holidayFilter.getMaxDate()) ?
                        holidayFilter.getMaxDate() : holiday.getEndDate();

                Long diffTotal = maxEndDate.getTime() - minStartDate.getTime();
                Long diff1 = holiday.getEndDate().getTime() - holiday.getStartDate().getTime();
                Long diff2 = holidayFilter.getMaxDate().getTime() - holidayFilter.getMaxDate().getTime();

                long timeInDaysTotalRange = TimeUnit.DAYS.convert(diffTotal, TimeUnit.MILLISECONDS);
                long time1 = TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS);
                long time2 = TimeUnit.DAYS.convert(diff2, TimeUnit.MILLISECONDS);

                if(timeInDaysTotalRange < time1 + time2) {
                    holidayDTOList.add(holiday);
                }
            }
        }

        //Apply Status Filter if list not empty
        if(holidayFilter.getStatusList() != null && !holidayFilter.getStatusList().isEmpty()) {
            holidayDTOList = holidayDTOList
                    .stream()
                    .filter(p -> holidayFilter.getStatusList().contains(p.getStatus()))
                    .collect(Collectors.toList());
        }

        //Apply employee filter if specified
        if(holidayFilter.getUserIdList() != null && !holidayFilter.getStatusList().isEmpty()) {
            holidayDTOList = holidayDTOList
                    .stream()
                    .filter(p -> holidayFilter.getUserIdList().contains(p.getUserId()))
                    .collect(Collectors.toList());
        }

        return holidayDTOList;

    };

}
