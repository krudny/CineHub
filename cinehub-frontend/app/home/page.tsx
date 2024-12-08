import Image from "next/image";
import Navbar from "@/app/components/Navbar";

const arr = [0, 1, 2, 3, 4, 5, 6, 7];

export default function Page() {
  return (
    <div>
      <div className="relative w-full h-screen flex items-center">
        <div className="w-full absolute top-0 z-20">
          <Navbar />
        </div>

        <div className="absolute inset-0 bg-black opacity-50 z-10"></div>
        <Image
          src="/the_witcher.jpg"
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
                The Witcher
              </p>
            </div>
            <div className="flex gap-4 my-4 font-oswald">
              <p className="text-white text-lg lg:text-2xl">Rating: 7.8</p>
              <p className="text-white text-lg lg:text-2xl">Duration: 2:15h</p>
              <p className="text-white text-lg lg:text-2xl">Year: 2020</p>
            </div>
            <div className="my-8">
              <p className="text-white text-md lg:text-xl">
                Lorem Ipsum is simply dummy text of the printing and typesetting
                industry. Lorem Ipsum has been the industry's standard dummy
                text ever since the 1500s, when an unknown printer took a galley
                of type and scrambled it to make a type specimen book. It has
                survived not only five centuries, but also the leap into
                electronic typesetting, remaining essentially unchanged. It was
                popularised in the 1960s.
              </p>
            </div>
            <div className="flex gap-6 mt-10">
              <button className="flex justify-center items-center px-4 py-3 bg-orange-500  rounded-3xl  transition duration-200 ease-in-out hover:bg-orange-600">
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
      </div>
      <div className="w-full bg-neutral-100">
        <div className="max-w-screen-lg mx-auto responsive my-20 px-6">
          <p className="font-oswald text-zinc-900 font-bold text-6xl">
            Trending films
          </p>
          <div className="grid grid-cols-2 gap-8 md:grid-cols-3 lg:grid-cols-4 md:gap-12 mt-10">
            {arr.map((number) => {
              return (
                <div
                  key={number}
                  className="relative aspect-[3/4] flex flex-col justify-end items-center"
                >
                  <div className="absolute inset-0 bg-black opacity-10 z-10 transition duration-100 ease-linear hover:opacity-40"></div>
                  <Image
                    src="/thumbnail1.jpg"
                    alt="thumbnail"
                    layout="fill"
                    objectFit="cover"
                    objectPosition="center"
                    priority
                  />
                  <p className="py-4 text-neutral-100 text-3xl font-bold font-oswald z-20">
                    The witcher
                  </p>
                  <div className="mb-4 z-20">
                    <span className="material-icons text-yellow-500">star</span>
                    <span className="material-icons text-yellow-500">star</span>
                    <span className="material-icons text-yellow-500">star</span>
                    <span className="material-icons text-yellow-500">star</span>
                    <span className="material-icons text-yellow-500">star</span>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
}
