package com.hyun.seoul_event.seoulevent.application;

import com.hyun.seoul_event.seoulevent.adapter.in.web.dto.SeoulEventListDto;
import com.hyun.seoul_event.seoulevent.application.port.in.ViewEventListUseCase;
import com.hyun.seoul_event.seoulevent.application.port.out.LoadEventPort;
import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewEventListService implements ViewEventListUseCase {

    private final LoadEventPort loadEventPort;

    @Override
    public SeoulEventListDto getEventList() {
        List<SeoulEvent> seoulEvents = loadEventPort.findTop5ByOrderByIdDesc();
        return SeoulEventListDto.of(seoulEvents);
    }

}
