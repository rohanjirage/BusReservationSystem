import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";

const ViewPassengerJourneyBooking = () => {
  const navigate = useNavigate();

  const [bookedJourneys, setBookedJourneys] = useState([]);

  var passenger = JSON.parse(sessionStorage.getItem("active-passenger"));

  const retrieveAllBookedJourneys = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/journey/book/fetch/user?userId=" + passenger.id
    );
    console.log(response.data);
    return response.data;
  };

  const convertToEpochTime = (dateString) => {
    const selectedDate = new Date(dateString);
    const epochTime = selectedDate.getTime();
    return epochTime;
  };

  useEffect(() => {
    const getAllBookedJourneys = async () => {
      const bookings = await retrieveAllBookedJourneys();
      if (bookings) {
        setBookedJourneys(bookings.bookings);
      }
    };

    getAllBookedJourneys();
  }, []);

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  return (
    <div className="mt-3">
      <div
        className="card form-card ms-2 me-2 mb-5 custom-bg border-color "
        style={{
          height: "45rem",
        }}
      >
        <div className="card-header custom-bg-text text-center bg-color">
          <h2>Booked Journeys</h2>
        </div>
        <div
          className="card-body"
          style={{
            overflowY: "auto",
          }}
        >
          <div className="table-responsive mt-3">
            <table className="table table-hover text-color text-center">
              <thead className="table-bordered border-color bg-color custom-bg-text">
                <tr>
                  <th scope="col">Booking Id</th>
                  <th scope="col">Journey Number</th>
                  <th scope="col">Bus</th>
                  <th scope="col">Bus Registration No.</th>
                  <th scope="col">Bus Departure Time</th>
                  <th scope="col">Bus Arrival Time</th>
                  <th scope="col">Source BusStop</th>
                  <th scope="col">Destination BusStop</th>
                  <th scope="col">Journey Seat</th>
                  <th scope="col">Seat Fare (Rs.)</th>
                  <th scope="col">Total Passenger</th>
                  <th scope="col">Booking Time</th>
                  <th scope="col">Status</th>
                </tr>
              </thead>
              <tbody>
                {bookedJourneys.map((book) => {
                  return (
                    <tr>
                      <td>
                        <b>{book.bookingId}</b>
                      </td>
                      <td>
                        <b>{book.journey.journeyNumber}</b>
                      </td>
                      <td>
                        <b>{book.journey.bus.name}</b>
                      </td>
                      <td>
                        <b>{book.journey.bus.registrationNumber}</b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(book.journey.busDepartureTime)}</b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(book.journey.busArrivalTime)}</b>
                      </td>
                      <td>
                        <b>{book.journey.departureBusStop.name}</b>
                      </td>
                      <td>
                        <b>{book.journey.arrivalBusStop.name}</b>
                      </td>
                      <td>
                        <b>{book.busSeatType}</b>
                      </td>
                      <td>
                        {(() => {
                          if (book.busSeatType === "Back") {
                            return <b>{book.journey.backSeatFare}</b>;
                          } else if (book.busSeatType === "Middle") {
                            return <b>{book.journey.middleSeatFare}</b>;
                          } else if (book.busSeatType === "Front") {
                            return <b>{book.journey.frontSeatFare}</b>;
                          }
                        })()}
                      </td>
                      <td>
                        <b>{book.totalPassengers}</b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(book.bookingTime)}</b>
                      </td>
                      <td>
                        <b>{book.status}</b>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewPassengerJourneyBooking;
