import { useState, useEffect } from "react";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";

const ViewAllBus = () => {
  const [allBus, setAllBus] = useState([]);

  const retrieveAllBuss = async () => {
    const response = await axios.get("http://localhost:8080/api/bus/fetch/all");
    console.log(response.data);
    return response.data;
  };

  useEffect(() => {
    const getAllBuss = async () => {
      const allBus = await retrieveAllBuss();
      if (allBus) {
        setAllBus(allBus.buses);
      }
    };

    getAllBuss();
  }, []);

  return (
    <div>
      <div className="mt-2">
        <div
          className="card form-card ms-5 me-5 mb-5 custom-bg border-color "
          style={{
            height: "30rem",
          }}
        >
          <div className="card-header custom-bg-text text-center bg-color">
            <h2>All Bus</h2>
          </div>
          <div
            className="card-body"
            style={{
              overflowY: "auto",
            }}
          >
            <div className="table-responsive">
              <table className="table table-hover text-color text-center">
                <thead className="table-bordered border-color bg-color custom-bg-text">
                  <tr>
                    <th scope="col">Bus</th>
                    <th scope="col">Registration Number</th>
                    <th scope="col">Bus Description</th>
                    <th scope="col">Total Seat</th>
                    <th scope="col">Total Back Seat</th>
                    <th scope="col">Total Middle Seat</th>
                    <th scope="col">Total Front Seat</th>
                  </tr>
                </thead>
                <tbody>
                  {allBus.map((bus) => {
                    return (
                      <tr>
                        <td>
                          <b>{bus.name}</b>
                        </td>

                        <td>
                          <b>{bus.registrationNumber}</b>
                        </td>
                        <td>
                          <b>{bus.description}</b>
                        </td>
                        <td>
                          <b>{bus.totalSeat}</b>
                        </td>
                        <td>
                          <b>{bus.backSeats}</b>
                        </td>
                        <td>
                          <b>{bus.middleSeats}</b>
                        </td>
                        <td>
                          <b>{bus.frontSeats}</b>
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
    </div>
  );
};

export default ViewAllBus;
