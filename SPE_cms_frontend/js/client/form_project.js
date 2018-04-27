const form_project_client = {
  data: function () {
    return {
      id: null,
      orgAddress: null,
      orgName: null,
      orgPhone: null,
      persEmail: null,
      persName: null,
      persPhone: null
    }
  },
  mounted: function(){

    //test purposes
    // this.$parent.username = "test_client";
    // this.$parent.token = "whvwbfolhqw!whvwbfolhqw";

    if (this.$parent.token != "None")
    {
      //getting client's data
      $.ajax({
        url: 'http://localhost:8080/client_id',
        method: 'POST',
        data: {
         id: this.$parent.username,
         login_token: this.$parent.token
        },
        success: function(data){
          // console.log(data);

          //setting the locals
          this.id = data.id;
          this.orgAddress = data.orgAddress;
          this.orgName = data.orgName;
          this.orgPhone = data.orgPhone;
          this.persEmail = data.persEmail;
          this.persName = data.persName;
          this.persPhone = data.persPhone;

          //filling the form
          //part 1
          document.getElementById('persName').value = this.persName;
          document.getElementById('persPhone').value = this.persPhone;
          document.getElementById('persEmail').value = this.persEmail;
          //part 2
          document.getElementById('orgAddress').value = this.orgAddress;
          document.getElementById('orgName').value = this.orgName;
          document.getElementById('orgPhone').value = this.orgPhone;
        },
        error: function(error){
          console.log(error);
        }
      });

      var current_fs, next_fs, previous_fs; //fieldsets
      var left, opacity, scale; //fieldset properties which we will animate
      var animating; //flag to prevent quick multi-click glitches

      $(".next").click(function(){
        if(animating) return false;
        animating = true;

        current_fs = $(this).parent().parent();
        next_fs = $(this).parent().parent().next();

        //activate next step on progressbar using the index of next_fs
        $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
        $("#progressbar li").eq($("fieldset").index(current_fs)).addClass("visited");
        //show the next fieldset
        next_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
          step: function(now, mx) {
            opacity = 1 - now;
            current_fs.css({
              'transform': 'scale('+scale+')',
              'position': 'absolute'
            });
            next_fs.css({'left': left, 'opacity': opacity});
          },
          duration: 350,
          complete: function(){
            current_fs.hide();
            animating = false;
          },
          //this comes from the custom easing plugin
          easing: 'easeInOutBack'
        });
      });

      $(".previous").click(function(){
        if(animating) return false;
        animating = true;

        current_fs = $(this).parent().parent();
        previous_fs = $(this).parent().parent().prev();

        //de-activate current step on progressbar
        $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
        $("#progressbar li").eq($("fieldset").index(previous_fs)).removeClass("visited");
        //show the previous fieldset
        previous_fs.show();
        //hide the current fieldset with style
        current_fs.animate({opacity: 0}, {
          step: function(now, mx) {
            opacity = 1 - now;
            current_fs.css({'left': left});
            previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
          },
          duration: 350,
          complete: function(){
            current_fs.hide();
            animating = false;
          },
          //this comes from the custom easing plugin
          easing: 'easeInSine'
        });
      });

      $(".submit").click(function(){
        return false;
      })
    }
    else {
      console.log("Not Logged In")
    }
  },
  methods: {
    submit : function(){
        //getting the info
        var tags = document.getElementById('tags').value;
        var title = document.getElementById('title').value;
        var content = document.getElementById('content').value;
        var imgUrl = document.getElementById('imgUrl').value;
        var license = document.getElementById('license').value;

        //submitting
        $.ajax({
          url: 'http://localhost:8080/project_save',
          method: 'POST',
          data: {
            title: title,
            tags: tags,
            content: content,
            imgUrl: imgUrl,
            license: license,
            clientId: this.$parent.username,
            login_token: this.$parent.token
          },
          success: function(data){
            console.log(data);
          },
          error: function(error){
            console.log(error);
          }
        });
    }
  },
  template: `
  <div class = "project-submit">
    <form id="msform">
      <ul id="progressbar">
        <li class="active">Personal</li>
        <li>Organisation</li>
        <li>Project Brief</li>
      </ul>
      <fieldset>
        <h2 class="fs-title">Personal Details</h2>
        <h3 class="fs-subtitle">Details about the organisation's contact person.</h3>

        <input type="text" class = "full" id="persName" name="name" disabled placeholder="Name" />
        <input type="text" class = "half" id="persPhone" name="phone" disabled placeholder="Phone" />
        <input type="text" class = "half" id="persEmail" name="email" disabled placeholder="E-mail" />
        <div class = "line" style = "text-align: center;">
          <input type="button" class = "next action-button" name="next"  value="Next" />
        </div>
      </fieldset>

      <fieldset>
        <h2 class="fs-title">Organisation Details</h2>
        <h3 class="fs-subtitle">Details about the organisation.</h3>
        <input type="text" class = "half" id="orgName" name="orgname" disabled placeholder="Organisation Name" />
        <input type="text" class = "half" id="orgPhone" name="orgphone" disabled placeholder="Organisation Phone" />
        <input type="text" class = "full" id="orgAddress" name="orgaddress" disabled placeholder="Organisation Address" />
        <div class = "line" style = "text-align: center;">
          <input type="button" class = "previous action-button" name="previous"  value="Previous" />
          <input type="button" class = "next action-button" name="next"  value="Next" />
        </div>
      </fieldset>

      <fieldset>
        <h2 class="fs-title">Project Brief</h2>
        <h3 class="fs-subtitle">Details regarding the project, that will be showcased to students.</h3>
        <input type="text" class = "full" name="title" id="title" placeholder="Project Name" />
        <input type="text" class = "full" name="tags" id="tags" placeholder="Tags separated by commas" />
        <textarea name="projdesc" class = "big full" id="content" placeholder="Detailed project description"></textarea>
        <input type="text" class = "half" name="imgUrl" id="imgUrl" placeholder="Image url" />
        <input type="text" class = "half" name="license" id="license" placeholder="Type of license" />
        <div class = "line" style = "text-align: center;">
          <input type="button" class = "previous action-button" name="previous"  value="Previous" />
          <input type="submit" class = "submit action-button" v-on:click = "submit()" name="submit"  value="Submit" />
        </div>
      </fieldset>
    </form>
  </div>`
};
