let header = document.querySelector("header")

window.addEventListener("scroll", () => {
  if(scrollY > 400){
    header.classList.add("sticky")    
  }else{
    if(header.classList.contains("sticky")){
      header.classList.remove("sticky")
    }
  }
})


$('.slider').slick({
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: true,
    dots: false,
    centerMode: true,
    slidesToShow: 3,
    centerPadding: '5px',
    prevArrow: '<button type="button" class="slick-prev">Previous</button>',
    nextArrow: '<button type="button" class="slick-next">Next</button>',

    responsive: [
        {
          breakpoint: 768,
          settings: {
            slidesToShow: 3
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1
          }
        }
      ]
  });