import Image from "next/image";
import { MovieResponse } from "@/app/types/interfaces";
import Link from "next/link";

async function getTrendingFilms() {
  const response = await fetch("http://localhost:8080/movies/trending", {
    cache: "force-cache",
  });
  return await response.json();
}

export default async function TrendingFilms() {
  const trending: MovieResponse[] = await getTrendingFilms();

  return (
    <div className="w-full bg-neutral-100">
      <div className="max-w-screen-lg mx-auto responsive my-10 px-6">
        <p className="font-oswald text-zinc-900 font-bold text-6xl">
          Trending films
        </p>
        <div className="grid grid-cols-2 gap-6 md:grid-cols-3 lg:grid-cols-4 mt-10">
          {trending.map((film: MovieResponse) => (
            <Link href={`/movie/${film.movieId}`} key={film.movieId}>
              <div className="w-full">
                <div className="relative aspect-[3/4]">
                  <Image
                    src={film.thumbnail_img}
                    alt="thumbnail"
                    fill
                    priority
                    className="w-full h-full object-cover object-center rounded-lg shadow-md"
                  />
                  <div className="absolute inset-0 bg-black opacity-10 hover:opacity-30 transition-opacity"></div>
                </div>
                <p className="mt-4 text-center text-lg font-bold text-gray-900 truncate">
                  {film.title}
                </p>
              </div>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
}
