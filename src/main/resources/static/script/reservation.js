let header = document.querySelector("header")

window.addEventListener("scroll", () => {
  if(scrollY > 320){
    header.classList.add("sticky")    
  }else{
    if(header.classList.contains("sticky")){
      header.classList.remove("sticky")
    }
  }
})