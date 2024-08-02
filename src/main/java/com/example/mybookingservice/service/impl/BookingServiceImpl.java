package com.example.mybookingservice.service.impl;

import com.example.mybookingservice.dto.booking.BookingByParametersRequestDto;
import com.example.mybookingservice.dto.booking.BookingResponseDto;
import com.example.mybookingservice.dto.booking.CreateBookingRequestDto;
import com.example.mybookingservice.dto.booking.UpdateBookingRequestDto;
import com.example.mybookingservice.exception.EntityNotFoundException;
import com.example.mybookingservice.mapper.BookingMapper;
import com.example.mybookingservice.model.Booking;
import com.example.mybookingservice.model.User;
import com.example.mybookingservice.repository.booking.BookingRepository;
import com.example.mybookingservice.repository.booking.BookingSpecificationBuilder;
import com.example.mybookingservice.repository.user.UserRepository;
import com.example.mybookingservice.service.BookingService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final BookingSpecificationBuilder bookingSpecificationBuilder;

    @Transactional
    @Override
    public BookingResponseDto createBooking(Long id, CreateBookingRequestDto requestDto) {
        Booking booking = bookingMapper.toModel(requestDto);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "can't find user by id:" + id));
        booking.setUser(user);
        booking.setStatus(Booking.StatusBooking.PENDING);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public List<BookingResponseDto> getBookingsByParameters(
            BookingByParametersRequestDto parametersRequestDto) {
        Specification<Booking> bookingSpecification =
                bookingSpecificationBuilder.build(parametersRequestDto);
        return bookingRepository.findAll(bookingSpecification)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Override
    public List<BookingResponseDto> getAllBookingsByUserId(Long id) {
        return bookingRepository.findAll().stream()
                        .filter(booking -> booking.getUser().getId().equals(id))
                        .map(booking -> bookingMapper.toDto(booking))
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("can't find booking by id:" + id));
        return bookingMapper.toDto(booking);
    }

    @Override
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BookingResponseDto updateBookingById(
            Long id, UpdateBookingRequestDto requestDto, User user) {
        Booking booking = bookingMapper.toUpdatedModel(requestDto);
        Booking bookingWithDataBase = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("can't find booking by id:" + id));
        booking.setAccommodation(bookingWithDataBase.getAccommodation());
        booking.setUser(user);
        booking.setId(id);
        booking.setStatus(bookingWithDataBase.getStatus());
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }
}
