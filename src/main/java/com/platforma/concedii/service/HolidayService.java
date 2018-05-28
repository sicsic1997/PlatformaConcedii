package com.platforma.concedii.service;

import com.platforma.concedii.dao.HolidayDAO;
import com.platforma.concedii.domain.HolidayFilter;
import com.platforma.concedii.domain.ReportLine;
import com.platforma.concedii.dto.HolidayDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

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
        List<HolidayDTO> holidayDTOList = HolidayDAO.getInstance().getAllHolidaysForUser(holidayDTO.getUserId());
        for (HolidayDTO holiday: holidayDTOList) {
            LocalDate minStartDate = holiday.getStartDate().isBefore(holidayDTO.getStartDate()) ?
                    holiday.getStartDate() : holidayDTO.getStartDate();
            LocalDate maxEndDate = holiday.getEndDate().isBefore(holidayDTO.getEndDate()) ?
                    holidayDTO.getEndDate() : holiday.getEndDate();

            Long diffTotal = DAYS.between(minStartDate, maxEndDate);
            Long diff1 = DAYS.between(holiday.getStartDate(),holiday.getEndDate());
            Long diff2 = DAYS.between(holidayDTO.getStartDate(), holidayDTO.getEndDate());

            if(diffTotal <= diff1 + diff2) {
                nrOverlapping = nrOverlapping + 1;
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String message = df.format(holiday.getStartDate()) + " to " + df.format(holiday.getEndDate());
                overlappingDatesToString.add(message);
            }
        }
        if(nrOverlapping == 0) {
            HolidayDAO.getInstance().saveHoliday(holidayDTO);
            return SAVE_HOLIDAY_SUCCESS;
        } else {
            return SAVE_HOLIDAY_OVERLAPS + String.join(", ", overlappingDatesToString);
        }
    }

    /**/
    public List<HolidayDTO> getAllHolidaysByFilter(HolidayFilter holidayFilter) {

        List<HolidayDTO> holidayDTOList = HolidayDAO.getInstance().getAllHolidays();

        //Apply DateRange Filter if date-range is set
        if(holidayFilter.isUseDateRange()) {
            List<HolidayDTO> auxHolidayDTOList = new ArrayList<>(holidayDTOList);
            holidayDTOList = new ArrayList<>();

            for (HolidayDTO holiday:auxHolidayDTOList) {
                LocalDate minStartDate = holiday.getStartDate().isBefore(holidayFilter.getMinDate()) ?
                        holiday.getStartDate() : holidayFilter.getMinDate();
                LocalDate maxEndDate = holiday.getEndDate().isBefore(holidayFilter.getMaxDate()) ?
                        holidayFilter.getMaxDate() : holiday.getEndDate();

                Long diffTotal = DAYS.between(minStartDate, maxEndDate);
                Long diff1 = DAYS.between(holiday.getStartDate(),holiday.getEndDate());
                Long diff2 = DAYS.between(holidayFilter.getMinDate(), holidayFilter.getMaxDate());

                if(diffTotal < diff1 + diff2) {
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
        if(holidayFilter.getUserIdList() != null && !holidayFilter.getUserIdList().isEmpty()) {
            holidayDTOList = holidayDTOList
                    .stream()
                    .filter(p -> holidayFilter.getUserIdList().contains(p.getUserId()))
                    .collect(Collectors.toList());
        }

        return holidayDTOList;

    };

    /**/
    public String generateReport() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_mm_yyyy_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        String fileName = "src/main/resources/reports/Holiday_Report_" + dtf.format(now);
        List<ReportLine> reportLineList = HolidayDAO.getInstance().getAllReportLines();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try(BufferedWriter bf = new BufferedWriter(new FileWriter(fileName))) {
            for (ReportLine line:reportLineList) {
                String row =
                        line.getFirstName() + " " +
                        line.getLastName() + ": " +
                        df.format(line.getStartDate()) + " to " +
                        df.format(line.getEndDate()) + " with status " +
                        line.getStatus() + ".\n";
                bf.write(row);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  fileName;

    }

}
