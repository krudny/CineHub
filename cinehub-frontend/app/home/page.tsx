import Hero from "@/app/components/Hero";
import Navbar from "@/app/components/Navbar";
import TrendingFilms from "@/app/components/TrendingFilms";
import { MovieResponse } from "@/app/types/interfaces";

export async function getTrendingFilms(): Promise<MovieResponse[]> {
  const response: Response = await fetch("http://localhost:8080/movies/trending", { next: { revalidate: 30 }})
  return await response.json();
}

export default async function Page() {
  const trending: MovieResponse[] = await getTrendingFilms();

  // during production version, random is prerendered
  const randomHero = Math.floor(Math.random() * trending.length)


  return (
    <div className="relative w-full select-none">
      <div className="w-full absolute top-0 z-20">
        <Navbar />
      </div>

      <Hero {...trending[randomHero]} />

      <TrendingFilms />
    </div>
  );
}
