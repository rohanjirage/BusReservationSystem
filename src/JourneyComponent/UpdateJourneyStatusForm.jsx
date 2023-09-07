import { useState, useEffect } from "react";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useLocation } from "react-router-dom";

const UpdateJourneyStatusForm = () => {
  const navigate = useNavigate();

  const location = useLocation();
  const journey = location.state;

  const [allStatus, setAllStatus] = useState([]);

  const [updateJourneyStatus, setUpdateJourneyStatus] = useState({
    journeyId: journey.id,
    status: "",
  });

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  const handleUserInput = (e) => {
    setUpdateJourneyStatus({
      ...updateJourneyStatus,
      [e.target.name]: e.target.value,
    });
  };

  const retrieveAllJourneyStatus = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/journey/status/all"
    );
    console.log(response.data);
    return response.data;
  };

  useEffect(() => {
    const getAllJourneyStatus = async () => {
      const allStatuses = await retrieveAllJourneyStatus();
      if (allStatuses) {
        setAllStatus(allStatuses);
      }
    };

    getAllJourneyStatus();
  }, []);

  const updateJourney = (e) => {
    fetch("http://localhost:8080/api/journey/update/status", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updateJourneyStatus),
    })
      .then((result) => {
        console.log("result", result);
        result.json().then((res) => {
          console.log(res);

          if (res.success) {
            console.log("Got the success response");

            toast.success(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });

            setTimeout(() => {
              navigate("/admin/journey/all");
            }, 1000); // Redirect after 3 seconds
          } else {
            console.log("Didn't got success response");
            toast.error("It seems server is down", {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            // setTimeout(() => {
            //   window.location.reload(true);
            // }, 1000); // Redirect after 3 seconds
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        // setTimeout(() => {
        //   window.location.reload(true);
        // }, 1000); // Redirect after 3 seconds
      });
    e.preventDefault();
  };

  return (
    <div>
      <div className="mt-2 d-flex aligns-items-center justify-content-center ms-2 me-2 mb-2">
        <div
          className="card form-card border-color text-color custom-bg"
          style={{ width: "50rem" }}
        >
          <div className="card-header bg-color custom-bg-text text-center">
            <h5 className="card-title">Add Journey</h5>
          </div>
          <div className="card-body">
            <form className="row g-3" onSubmit={updateJourney}>
              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Bus</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.bus.name}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Departure BusStop</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.departureBusStop.name}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Arrival BusStop</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.arrivalBusStop.name}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Journey Status</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.status}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Bus Departure Time</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={formatDateFromEpoch(journey.busDepartureTime)}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Bus Arrival Time</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={formatDateFromEpoch(journey.busArrivalTime)}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Back Seat Fare</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.backSeatFare}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Middle Seat Fare</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.middleSeatFare}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Front Seat Fare</b>
                </label>
                <input
                  type="text"
                  className="form-control"
                  value={journey.frontSeatFare}
                  readOnly
                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="trainId" className="form-label">
                  <b>Journey Status</b>
                </label>
                <select
                  onChange={handleUserInput}
                  className="form-control"
                  name="status"
                >
                  <option value="">Select Status</option>

                  {allStatus.map((status) => {
                    return <option value={status}> {status} </option>;
                  })}
                </select>
              </div>

              <div className="d-flex aligns-items-center justify-content-center">
                <input
                  type="submit"
                  className="btn bg-color custom-bg-text"
                  value="Update Journey Status"
                />
              </div>
              <ToastContainer />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateJourneyStatusForm;
