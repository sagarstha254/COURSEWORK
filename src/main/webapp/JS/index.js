var slideIndex = 0;
      var slides = document.getElementsByClassName("slide");

      showSlide(slideIndex);

      setInterval(function() {
        slideIndex++;
        if (slideIndex >= slides.length) {
          slideIndex = 0;
        }
        showSlide(slideIndex);
      }, 3000);

      function showSlide(index) {
        for (var i = 0; i < slides.length; i++) {
          slides[i].style.display = "none";
        }
        slides[index].style.display = "block";
      }