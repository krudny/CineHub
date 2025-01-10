"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { logout } from "@/app/utils/functions";

export default function Navbar() {
  const router = useRouter();

  return (
    <div className="w-full flex flex-col md:flex-row justify-between items-center text-neutral-100 px-16">
      <Link href="/">
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
        <Link href="/auth/login">
          <p className="relative group">
            Login
            <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
          </p>
        </Link>
        <Link href="/auth/register">
          <p className="relative group">
            Register
            <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
          </p>
        </Link>
        <p
          className="relative group cursor-pointer"
          onClick={() => logout(router)}
        >
          Logout
          <span className="absolute left-0 -bottom-1 w-0 h-[2px] bg-neutral-100 transition-all duration-300 group-hover:w-full"></span>
        </p>
      </div>
    </div>
  );
}
