import Link from "next/link";

export default function Navbar() {
  return (
    <div className="w-full flex flex-col md:flex-row justify-between items-center text-neutral-100 px-16 ">
      <Link href="/home">
      <div className="flex py-6 font-oswald text-2xl md:text-4xl">
        <p className="p-1 md:py-1.5">Cine</p>
        <p className="bg-orange-500 rounded-lg md:mx-1 p-1 md:p-1.5">Hub</p>
      </div>
      </Link>
      <div className="flex items-center font-oswald text-md md:text-2xl gap-x-4 sm:gap-x-10">
        <p className="relative group">
          Menu 1
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        <p className="relative group">
          Menu 2
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        <p className="relative group">
          Menu 3
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        <p className="relative group">
          Menu 4
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
        {/*<span className="material-icons mr-8 cursor-pointer">*/}
        {/*  <p className="text-4xl">search</p>*/}
        {/*</span>*/}
      </div>
    </div>
  );
}
