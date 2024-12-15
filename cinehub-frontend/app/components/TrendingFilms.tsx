import Image from "next/image";
import {MovieResponse} from "@/app/types/interfaces";
import Link from "next/link";

async function getTrendingFilms() {
    const response = await fetch("http://localhost:8080/movies/trending");
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
        <div className="grid grid-cols-2 gap-8 md:grid-cols-3 lg:grid-cols-4 md:gap-12 mt-10">
          {trending.map((film: MovieResponse) => {
            return (
                <Link href={`/movie/${film.movieId}`} key={film.movieId}>
                <div>
                    <p className="py-4 text-zinc-900 text-center text-3xl font-bold font-oswald z-10">
                      {film.title}
                    </p>
                      <div
                          key={film.movieId}
                          className="relative aspect-[3/4] flex flex-col justify-end items-center"
                      >
                        <div className="absolute inset-0 bg-black opacity-10 z-20 transition duration-100 ease-linear hover:opacity-30"></div>
                        <Image
                          src={film.thumbnail_img}
                          alt="thumbnail"
                          layout="fill"
                          objectFit="cover"
                          objectPosition="center"
                          priority
                        />
                        {/*<div className="mb-4 z-10">*/}
                        {/*  <span className="material-icons text-yellow-500">star</span>*/}
                        {/*  <span className="material-icons text-yellow-500">star</span>*/}
                        {/*  <span className="material-icons text-yellow-500">star</span>*/}
                        {/*  <span className="material-icons text-yellow-500">star</span>*/}
                        {/*  <span className="material-icons text-yellow-500">star</span>*/}
                        {/*</div>*/}
                      </div>
                </div>
                </Link>
            );
          })}
        </div>
      </div>
    </div>
  );
}


