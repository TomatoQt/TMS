package zjut.pojo;

import java.util.Date;

public class Circle {
    private String type;
    private String handler;
    private String applicanter;
    private String finaler;
    private Date submit_time;
    private Date handle_time;
    private Date final_time;

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getHandler() {
                return handler;
        }

        public void setHandler(String handler) {
                this.handler = handler;
        }

        public String getApplicanter() {
                return applicanter;
        }

        public void setApplicanter(String applicanter) {
                this.applicanter = applicanter;
        }

        public String getFinaler() {
                return finaler;
        }

        public void setFinaler(String finaler) {
                this.finaler = finaler;
        }

        public Date getSubmit_time() {
                return submit_time;
        }

        public void setSubmit_time(Date submit_time) {
                this.submit_time = submit_time;
        }

        public Date getHandle_time() {
                return handle_time;
        }

        public void setHandle_time(Date handle_time) {
                this.handle_time = handle_time;
        }

        public Date getFinal_time() {
                return final_time;
        }

        public void setFinal_time(Date final_time) {
                this.final_time = final_time;
        }
}
