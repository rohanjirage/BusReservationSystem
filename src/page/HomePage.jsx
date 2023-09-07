import Carousel from "./Carousel";
import Footer from "./Footer";
import { Link } from "react-router-dom";
import travel_1 from "../images/bus_1.png";
import travel_2 from "../images/bus_2.png";

const HomePage = () => {
  return (
    <div className="container-fluid mb-2">
      <Carousel />

      <div className="container mt-5">
        <div className="row">
          <div className="col-md-8">
            <h1 className="text-color">Welcome to EASY BUS</h1>
            <p>
              Welcome to our advanced Bus Reservation System! We are thrilled to
              provide you with a seamless and convenient way to book your bus
              journeys. With our user-friendly interface, you can effortlessly
              browse through various routes, select preferred seats, and secure
              your tickets online. Say goodbye to long queues and last-minute
              worries 
            </p>
            <p>
              Our system offers real-time availability, secure payment options,
              and instant confirmations, guaranteeing your seat on the desired
              bus. With a dedicated support team, we're committed to making your
              travel as comfortable as your destination. Embrace the future of
              bus booking with us!
            </p>

          </div>
          <div className="col-md-4">
            <img
              src={travel_2}
              alt="Logo"
              width="400"
              height="auto"
              className="home-image"
            />
          </div>
        </div>

        <div className="row mt-5">
          <div className="col-md-4">
            <img
              src={travel_1}
              alt="Logo"
              width="400"
              height="auto"
              className="home-image"
            />
          </div>

        </div>
      </div>
      <hr />
      
    </div>
  );
};

export default HomePage;
