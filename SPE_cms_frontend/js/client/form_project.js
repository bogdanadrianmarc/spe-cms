const form_project = {
  data: function () {
    return {
      username: null,
      password: null
    }
  },
  mounted: function(){
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

  },
  methods: {
    
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

        <input type="text" class = "half" name="firstname" placeholder="First Name" />
        <input type="text" class = "half" name="lastname" placeholder="Last Name" />
        <input type="text" class = "half" name="phone" placeholder="Phone" />
        <input type="text" class = "half" name="email" placeholder="E-mail" />
        <div class = "line">
          <input type="button" class = "half" name="next" class="next action-button" value="Next" />
        </div>
      </fieldset>

      <fieldset>
        <h2 class="fs-title">Organisation Details</h2>
        <h3 class="fs-subtitle">Details about the organisation.</h3>
        <input type="text" class = "half" name="orgname" placeholder="Organisation Name" />
        <input type="text" class = "half" name="industry" placeholder="Industry" />
        <input type="text" class = "third" name="country" placeholder="Country" />
        <input type="text" class = "third" name="city" placeholder="City" />
        <input type="text" class = "third" name="zipcode" placeholder="Postcode" />
        <textarea name="address" placeholder="Address"></textarea>
        <div class = "line">
          <input type="button" class = "half" name="previous" class="previous action-button" value="Previous" />
          <input type="button" class = "half" name="next" class="next action-button" value="Next" />
        </div>
      </fieldset>

      <fieldset>
        <h2 class="fs-title">Project Brief</h2>
        <h3 class="fs-subtitle">Details regarding the project, that will be showcased to students.</h3>
        <input type="text" class = "half" name="projname" id="projname" placeholder="Project Name" />
        <input type="text" class = "half" name="tags" id="tags" placeholder="Tags separated by commas" />
        <textarea name="projdesc" class = "big" id="projdesc" placeholder="Detailed project description"></textarea>
        <div class = "line">
          <input type="button" class = "half" name="previous" class="previous action-button" value="Previous" />
          <input type="submit" class = "half" name="submit" class="submit action-button" value="Submit" />
        </div>
      </fieldset>
    </form>
  </div>`
};
