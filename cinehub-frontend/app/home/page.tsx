import Navbar from "@/app/components/Navbar";
import TrendingFilms from "@/app/components/TrendingFilms";
import Hero from "@/app/components/Hero";
import { MovieResponse } from "@/app/types/interfaces";

async function getFeaturedHero(): Promise<MovieResponse> {
  const response: Response = await fetch("http://localhost:8080/movies/77", {
    cache: "force-cache",
  });
  return await response.json();
}

export default async function Page() {
  const heroMovie: MovieResponse = await getFeaturedHero();
  console.log(heroMovie);

  return (
    <div className="relative w-full select-none">
      <div className="w-full absolute top-0 z-20">
        <Navbar />
      </div>

      <Hero {...heroMovie} />

      <TrendingFilms />
    </div>
  );
}
