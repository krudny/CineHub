import LayoutProps from "@/app/types/interfaces";
import Navbar from "@/app/components/Navbar";

export default function Page({ children }: LayoutProps) {
  return (
    <div className="w-screen h-md:h-screen flex flex-col flex-1 bg-zinc-900">
      <Navbar />

      {children}

      <div className="w-full flex justify-center">
        <div className="w-96 text-center">
          <p className="text-orange-500 font-oswald font-bold mb-4">
            CineHub - Your movie center
          </p>
        </div>
      </div>
    </div>
  );
}
