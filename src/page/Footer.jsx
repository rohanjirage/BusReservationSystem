import { Link } from "react-router-dom";
const Footer = () => {
  return (
    <div>
      <div class="container my-5">
        <footer class="text-center text-lg-start text-color">
          <div class="container-fluid p-4 pb-0">
            <section class="">
              <div class="row">
                <div class="col-lg-4 col-md-6 mb-4 mb-md-0">
                  <h5 class="text-uppercase text-color">
                    Bus Reservation 
                  </h5>

                  <p>
                    Welcome to our advanced Bus Reservation System! We are
                    thrilled to provide you with a seamless and convenient way
                    to book your bus journeys.
                  </p>
                </div>

                <div class="col-lg-5 ">
                  <h5 class="text-uppercase text-color-4">About us</h5>
                  <p>	The Easy Bus is a comprehensive software solution designed to streamline and automate the process of bus reservation and management for both passengers and bus operators. The system aims to provide an efficient and user-friendly platform that enables passengers to book bus tickets, and bus operators to manage their fleet, schedules, and reservations effectively. </p>
                  
                </div>

              </div>
            </section>

            <hr class="mb-4" />

            <section class="">
              <p class="d-flex justify-content-center align-items-center">
                <span class="me-3 text-color">Login from here</span>
                <Link to="/user/login" class="active">
                  <button
                    type="button"
                    class="btn btn-outline-light btn-rounded bg-color custom-bg-text"
                  >
                    Log in
                  </button>
                  </Link>
                <Link  to="/user/passenger/register">
                <button
                    type="button"
                    class="btn btn-outline-light btn-rounded bg-color custom-bg-text"
                  >
                    New User
                  </button>
                  </Link>
              </p>
            </section>

            <hr class="mb-4" />
          </div>

          <div class="text-center">
            © 2023 : Developed by Sanyam
                      
          </div>
        </footer>
      </div>
    </div>
  );
};

export default Footer;
