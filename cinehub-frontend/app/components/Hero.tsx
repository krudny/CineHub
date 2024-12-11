"use client";
import Image from "next/image";
import HeroProps from "@/app/types/interfaces";

export default function Hero(props: HeroProps) {
  const handleScroll = () => {
    window.scrollTo({
      top: window.innerHeight,
      behavior: "smooth",
    });
  };

  return (
    <div className="relative w-full h-screen">
      <div className="absolute inset-0 bg-black opacity-50 z-10"></div>
      <Image
        src={props.url}
        alt="Hero background"
        layout="fill"
        objectFit="cover"
        objectPosition="center"
        priority
      />

      <div className="absolute w-full lg:w-3/5 h-screen flex justify-center items-center z-20">
        <div className="mx-16">
          <div>
            <p className="text-white text-5xl lg:text-7xl 3xl:text-8xl font-bold font-oswald">
              {props.title}
            </p>
          </div>
          <div className="flex gap-4 my-4 font-oswald">
            <p className="text-white text-lg lg:text-2xl">
              Rating: {props.rating}
            </p>
            <p className="text-white text-lg lg:text-2xl">
              Duration: {props.duration}
            </p>
            <p className="text-white text-lg lg:text-2xl">Year: {props.year}</p>
          </div>
          <div className="my-8">
            <p className="text-white text-md lg:text-xl">{props.description}</p>
          </div>
          <div className="flex gap-6 mt-10">
            <button className="flex justify-center items-center px-4 py-3 bg-orange-500 rounded-3xl transition duration-200 ease-in-out hover:bg-orange-600">
              <span className="material-icons">play_arrow</span>
              <p className="text-zinc-900 font-bold text-md lg:text-lg">
                Watch Trailer
              </p>
            </button>
            <button className="flex justify-center items-center px-4 py-3 bg-neutral-100 rounded-3xl transition duration-200 ease-in-out hover:bg-neutral-300">
              <span className="material-icons">add</span>
              <p className="text-zinc-900 font-bold text-md lg:text-lg">
                Buy Ticket
              </p>
            </button>
          </div>
        </div>
      </div>

      <div className="absolute bottom-10 left-1/2 z-30 transform -translate-x-1/2">
        <div
          className="border-neutral-100 text-neutral-100 border-2 rounded-full flex justify-center items-center transition duration-150 ease-in-out hover:bg-neutral-300 hover:text-zinc-900 hover:cursor-pointer"
          onClick={handleScroll}
        >
          <span className="material-icons p-2">
            <p className="text-5xl">keyboard_double_arrow_down</p>
          </span>
        </div>
      </div>
    </div>
  );
}
