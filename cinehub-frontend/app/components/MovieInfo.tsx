import Image from "next/image";
import { convertToHours } from "@/app/utils/functions";
import { MovieResponse } from "@/app/types/interfaces";
import Screening from "@/app/components/Screening";

export default function MovieInfo(movie: MovieResponse) {
  return (
    <div className="flex max-w-7xl mx-auto mt-8 select-none ">
      <div className="w-1/3 h p-8">
        <div className="h-full relative aspect-[3/4] flex justify-center items-center">
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

      <div className="w-2/3 p-8 flex flex-col justify-between text-neutral-100 ">
        <div>
          <h1 className="font-oswald font-bold text-6xl">{movie.title}</h1>
          <div className="flex gap-x-3 mt-5 text-lg items-center">
            <p className="font-bold">Production:</p>
            <p> {movie.production}</p>
          </div>
        </div>

        <div className="mt-4 flex gap-x-4">
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

        <div className="my-4">
          <p className="text-lg">{movie.description}</p>
        </div>

        <Screening {...movie} />
      </div>
    </div>
  );
}
