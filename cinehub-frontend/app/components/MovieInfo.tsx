import Image from "next/image";
import {convertToHours} from "@/app/utils/functions";
import {MovieResponse} from "@/app/types/interfaces";

export default function MovieInfo(movie: MovieResponse) {
  return (
      <div className="flex max-w-7xl mx-auto mt-12">
        <div className="w-1/3 p-8">
          <div className="relative aspect-[3/4] flex justify-center items-center">
            <Image
                src={movie.thumbnail_img}
                alt="thumbnail"
                layout="fill"
                objectFit="cover"
                objectPosition="center"
                priority
                className="rounded-2xl"
            />
          </div>
        </div>

        <div className="w-2/3 p-8 flex flex-col text-neutral-100">
          <div>
            <h1 className="font-oswald font-bold text-6xl">
              {movie.title}
            </h1>
            <p className="mt-4 text-lg">Director: {movie.director}</p>
          </div>

          <div className="mt-8 flex gap-x-4">
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Rating: TODO
            </div>
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Duration: {convertToHours(movie.duration)}
            </div>
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Year: {movie.publishDate}
            </div>
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              {movie.genre.name}
            </div>
          </div>

          <div className="my-8">
            <p className="text-lg">
              {movie.description}
            </p>
          </div>
        </div>
      </div>
  )
}