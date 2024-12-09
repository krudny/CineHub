import Image from "next/image";

const arr = [0, 1, 2, 3, 4, 5, 6, 7];

export default function TrendingFilms() {
  return (
    <div className="w-full bg-neutral-100">
      <div className="max-w-screen-lg mx-auto responsive my-10 px-6">
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
                <div className="absolute inset-0 bg-black opacity-10 z-20 transition duration-100 ease-linear hover:opacity-30"></div>
                <Image
                  src="/thumbnail1.jpg"
                  alt="thumbnail"
                  layout="fill"
                  objectFit="cover"
                  objectPosition="center"
                  priority
                />
                <p className="py-4 text-neutral-100 text-3xl font-bold font-oswald z-10">
                  The Witcher
                </p>
                <div className="mb-4 z-10">
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
  );
}
