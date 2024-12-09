import Navbar from "@/app/components/Navbar";
import TrendingFilms from "@/app/components/TrendingFilms";
import Hero from "@/app/components/Hero";
import {heroMovie} from "@/app/utils/data";

export default function Page() {


  return (
    <div className="relative w-full">
      <div className="w-full absolute top-0 z-20">
        <Navbar />
      </div>

      <Hero {...heroMovie}/>

      <TrendingFilms />
    </div>
  );
}
